package copy.cat;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingQueue<E> {
  static class Node<E> {
    E item;

    Node<E> next;

    Node(E x) { item = x; }
  }

  /** The capacity bound, or Integer.MAX_VALUE if none */
  private final int capacity;

  /** Current number of elements */
  private final AtomicInteger count = new AtomicInteger();

  /**
   * Head of linked list.
   * Invariant: head.item == null
   */
  private transient Node<E> head;

  /**
   * Tail of linked list.
   * Invariant: last.next == null
   */
  private transient Node<E> last;

  /** Lock held by take, poll, etc */
  private final ReentrantLock takeLock = new ReentrantLock();

  /** Wait queue for waiting takes */
  private final Condition notEmpty = takeLock.newCondition();

  /** Lock held by put, offer, etc */
  private final ReentrantLock putLock = new ReentrantLock();

  /** Wait queue for waiting puts */
  private final Condition notFull = putLock.newCondition();

  /**
   * Signals a waiting take. Called only from put/offer (which do not
   * otherwise ordinarily lock takeLock.)
   */
  private void signalNotEmpty() {
    final ReentrantLock takeLock = this.takeLock;
    takeLock.lock();
    try {
      notEmpty.signal();
    } finally {
      takeLock.unlock();
    }
  }

  /**
   * Signals a waiting put. Called only from take/poll.
   */
  private void signalNotFull() {
    final ReentrantLock putLock = this.putLock;
    putLock.lock();
    try {
      notFull.signal();
    } finally {
      putLock.unlock();
    }
  }

  /**
   * Links node at end of queue.
   *
   * @param node the node
   */
  private void enqueue(Node<E> node) {
    last.next = node;
    last = last.next;
  }

  /**
   * Removes a node from head of queue.
   *
   * @return the node
   */
  private E dequeue() {
    Node<E> h = head;
    Node<E> first = h.next;
    h.next = h; // help GC
    head = first;
    E x = first.item;
    first.item = null;
    return x;
  }

  void fullyLock() {
    putLock.lock();
    takeLock.lock();
  }

  void fullyUnlock() {
    takeLock.unlock();
    putLock.unlock();
  }

  public LinkedBlockingQueue() {
    this(Integer.MAX_VALUE);
  }

  public LinkedBlockingQueue(int capacity) {
    if (capacity <= 0) throw new IllegalArgumentException();
    this.capacity = capacity;
    last = head = new Node<E>(null);
  }

  public LinkedBlockingQueue(Collection<? extends E> c) {
    this(Integer.MAX_VALUE);
    final ReentrantLock putLock = this.putLock;
    putLock.lock(); // Never contended, but necessary for visibility
    try {
      int n = 0;
      for (E e : c) {
        if (e == null) throw new NullPointerException();
        if (n == capacity) throw new IllegalStateException("Queue full");
        enqueue(new Node<E>(e));
        ++n;
      }
      count.set(n);
    } finally {
      putLock.unlock();
    }
  }

  public int size() {
    return count.get();
  }

  public int remainingCapacity() {
    return capacity - count.get();
  }

  public void put(E e) throws InterruptedException {
    if (e == null) throw new NullPointerException();

    int c = -1;
    Node<E> node = new Node<E>(e);
    final ReentrantLock putLock = this.putLock;
    final AtomicInteger count = this.count;
    putLock.lockInterruptibly();
    try {
      while (count.get() == capacity)
        notFull.await();
      enqueue(node);
      c = count.getAndIncrement();
      if (c + 1 < capacity) notFull.signal();
    } finally {
      putLock.unlock();
    }
    if (c == 0) signalNotEmpty();
  }

  public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
    if (e == null) throw new NullPointerException();
    long nanos = unit.toNanos(timeout);
    int c = -1;
    final ReentrantLock putLock = this.putLock;
    final AtomicInteger count = this.count;
    putLock.lockInterruptibly();
    try {
      while (count.get() == capacity) {
        if (nanos <= 0) return false;
        notFull.awaitNanos(nanos);
      }
      enqueue(new Node<E>(e));
      c = count.getAndIncrement();
      if (c + 1 < capacity) notFull.signal();
    } finally {
      putLock.unlock();
    }
    if (c == 0) signalNotEmpty();
    return true;
  }

  public boolean offer(E e) {
    if (e == null) throw new NullPointerException();
    final AtomicInteger count = this.count;
    if (count.get() == capacity) return false;

    int c = -1;
    Node<E> node = new Node<E>(e);
    final ReentrantLock putLock = this.putLock;
    putLock.lock();
    try {
      if (count.get() < capacity) {
        enqueue(node);
        c = count.getAndIncrement();
        if (c + 1 < capacity) notFull.signal();
      }
    } finally {
      putLock.unlock();
    }
    if (c == 0) signalNotEmpty();
    return c >= 0;
  }

  public E poll(long timeout, TimeUnit unit) throws InterruptedException {
    E x = null;
    int c = -1;
    long nanos = unit.toNanos(timeout);
    final ReentrantLock takeLock = this.takeLock;
    final AtomicInteger count = this.count;
    takeLock.lockInterruptibly();
    try {
      while (count.get() == 0) {
        if (nanos <= 0) return null;
        notEmpty.awaitNanos(nanos);
      }
      x = dequeue();
      c = count.getAndDecrement();
      if (c > 1) notEmpty.signal();
    } finally {
      takeLock.unlock();
    }
    if (c == capacity) signalNotFull();
    return x;
  }

  public E poll() {
    E x = null;
    int c = -1;
    final ReentrantLock takeLock = this.takeLock;
    final AtomicInteger count = this.count;
    takeLock.lock();
    try {
      if (count.get() > 0) {
        x = dequeue();
        c = count.getAndDecrement();
        if (c > 1) notEmpty.signal();
      }
    } finally {
      takeLock.unlock();
    }
    if (c == capacity) signalNotFull();
    return x;
  }

  public E peek() {
    if (count.get() == 0) return null;
    final ReentrantLock takeLock = this.takeLock;
    takeLock.lock();
    try {
      Node<E> first = head.next;
      if (first == null) return null;
      else return first.item;
    } finally {
      takeLock.unlock();
    }
  }

  void unlink(Node<E> p, Node<E> trail) {
    p.item = null;
    trail.next = p.next;
    if (last == p) last = trail;
    if (count.getAndDecrement() == capacity) notFull.signal();
  }

  public boolean remove(Object o) {
    if (o == null) return false;
    fullyLock();
    try {
      for (Node<E> trail = head, p = trail.next;
          p != null;
          trail = p, p = p.next) {
        if (o.equals(p.item)) {
          unlink(p, trail);
          return true;
        }
      }
      return false;
    } finally {
      fullyUnlock();
    }
  }

  public boolean contains(Object o) {
    if (o == null) return false;
    fullyLock();
    try {
      for (Node<E> p = head.next; p != null; p = p.next)
        if (o.equals(p.item))
          return true;
      return false;
    } finally {
      fullyUnlock();
    }
  }

  public Object[] toArray() {
    fullyLock();
    try {
      int size = count.get();
      Object[] a = new Object[size];
      int k = 0;
      for (Node<E> p = head.next; p != null; p = p.next)
        a[k++] = p.item;
      return a;
    } finally {
      fullyUnlock();
    }
  }

  public void clear() {
    fullyLock();
    try {
      for (Node<E> p, h = head; (p = h.next) != null; h = p) {
        h.next = h;
        p.item = null;
      }
      head = last;
      if (count.getAndSet(0) == capacity) notFull.signal();
    } finally {
      fullyUnlock();
    }
  }
}
