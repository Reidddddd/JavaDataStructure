package copy.cat;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<E> {
  /** The queued items */
  final Object[] items;

  /** items index for next take, poll, peek or remove */
  int takeIndex;

  /** items index for next put, offer or add */
  int putIndex;

  /** Numbers of elements in the queue */
  int count;

  /**
   * two-condition algorithm
   */

  /** Main lock guarding all access */
  final ReentrantLock lock;

  /** Conditions for waiting takes */
  private final Condition notEmpty;

  /** Conditions for waiting puts */
  private final Condition notFull;

  final int dec(int i) {
    return ((i == 0) ? items.length : i) - 1;
  }

  final E itemAt(int i) {
    return (E) itemAt(i);
  }

  private static void checkNotNull(Object v) {
    if (v == null) throw new NullPointerException();
  }

  /**
   * Inserts element at current put position, advances and signals. Call only when holding lock.
   */
  private void enqueue(E x) {
    final Object[] items = this.items;
    items[putIndex] = x;
    if (++putIndex == items.length) putIndex = 0;
    count++;
    notEmpty.signal();
  }

  /**
   * Extracts element at current take position, advances, and signals. Call only when holding lock.
   */
  private E dequeue() {
    final Object[] items = this.items;
    @SuppressWarnings("unchecked")
    E x = (E) items[takeIndex];
    items[takeIndex] = null;
    if (++takeIndex == items.length) takeIndex = 0;
    count--;
    notFull.signal();
    return x;
  }

  /**
   * Deletes item at array index removeIndex. Utility for remove(Object) and iterator.remove. Call
   * only when holding lock.
   */
  void removeAt(final int removeIndex) {
    final Object[] items = this.items;
    if (removeIndex == takeIndex) {
      items[takeIndex] = null;
      if (++takeIndex == items.length) takeIndex = 0;
      count--;
    } else {
      final int putIndex = this.putIndex;
      for (int i = removeIndex;;) {
        int next = i + 1;
        if (next == items.length) next = 0;
        if (next != putIndex) {
          items[i] = items[next];
          i = next;
        } else {
          items[i] = null;
          this.putIndex = i;
          break;
        }
      }
      count--;
    }
    notFull.signal();
  }

  public ArrayBlockingQueue(int capacity) {
    this(capacity, false);
  }

  public ArrayBlockingQueue(int capacity, boolean fair) {
    if (capacity <= 0) throw new IllegalArgumentException();
    this.items = new Object[capacity];
    lock = new ReentrantLock(fair);
    notEmpty = lock.newCondition();
    notFull = lock.newCondition();
  }

  public ArrayBlockingQueue(int capacity, boolean fair, Collection<? extends E> c) {
    this(capacity, fair);

    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      int i = 0;
      try {
        for (E e : c) {
          checkNotNull(e);
          items[i++] = e;
        }
      } catch (ArrayIndexOutOfBoundsException ex) {
        throw new IllegalArgumentException();
      }
      count = i;
      putIndex = i == capacity ? 0 : i;
    } finally {
      lock.unlock();
    }
  }

  public boolean add(E e) {
    if (offer(e)) return true;
    else throw new IllegalStateException("Queue full");
  }

  /**
   * Inserts the specified element at the tail of this queue if it is possible to do so immediately
   * without exceeding the queue's capacity, returning {@code true} upon success and {@code false}
   * if this queue is full. This method is generally preferable to method {@link #add}, which can
   * fail to insert an element only by throwing an exception.
   */
  public boolean offer(E e) {
    checkNotNull(e);
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      if (count == items.length) return false;
      else {
        enqueue(e);
        return true;
      }
    } finally {
      lock.unlock();
    }
  }

  /**
   * Inserts the specified element at the tail of this queue, waiting for space to become available
   * if the queue is full.
   * @throws InterruptedException
   */
  public void put(E e) throws InterruptedException {
    checkNotNull(e);
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      while (count == items.length)
        notFull.await();
      enqueue(e);
    } finally {
      lock.unlock();
    }
  }

  /**
   * Inserts the specified element at the tail of this queue, waiting up to the specified wait time
   * for space to become available if the queue is full.
   * @throws InterruptedException
   */
  public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
    checkNotNull(e);
    long nanos = unit.toNanos(timeout);
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      while (count == items.length) {
        if (nanos <= 0) return false;
        nanos = notFull.awaitNanos(nanos);
      }
      enqueue(e);
      return true;
    } finally {
      lock.unlock();
    }
  }

  public E poll() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      return count == 0 ? null : dequeue();
    } finally {
      lock.unlock();
    }
  }

  public E take() throws InterruptedException {
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      while (count == 0)
        notEmpty.await();
      return dequeue();
    } finally {
      lock.unlock();
    }
  }

  public E poll(int timeout, TimeUnit unit) throws InterruptedException {
    long nanos = unit.toNanos(timeout);
    final ReentrantLock lock = this.lock;
    try {
      while (count == 0) {
        if (nanos <= 0) return null;
        notEmpty.awaitNanos(nanos);
      }
      return dequeue();
    } finally {
      lock.unlock();
    }
  }

  public E peek() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      return itemAt(takeIndex);
    } finally {
      lock.unlock();
    }
  }

  public int size() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      return count;
    } finally {
      lock.unlock();
    }
  }

  public int remainingCapacity() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      return items.length - count;
    } finally {
      lock.unlock();
    }
  }

  /**
   * Removes a single instance of the specified element from this queue, if it is present. More
   * formally, removes an element {@code e} such that {@code o.equals(e)}, if this queue contains
   * one or more such elements. Returns {@code true} if this queue contained the specified element
   * (or equivalently, if this queue changed as a result of the call).
   * <p>
   * Removal of interior elements in circular array based queues is an intrinsically slow and
   * disruptive operation, so should be undertaken only in exceptional circumstances, ideally only
   * when the queue is known not to be accessible by other threads.
   */
  public boolean remove(Object o) {
    if (o == null) return false;
    final Object[] items = this.items;
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      if (count > 0) {
        final int putIndex = this.putIndex;
        int i = takeIndex;
        do {
          if (o.equals(items[i])) {
            removeAt(i);
            return true;
          }
          if (++i == items.length) i = 0;
        } while (i != putIndex);
      }
      return false;
    } finally {
      lock.unlock();
    }
  }

  public boolean contains(Object o) {
    if (o == null) return false;
    final Object[] items = this.items;
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      if (count > 0) {
        final int putIndex = this.putIndex;
        int i = takeIndex;
        do {
          if (o.equals(items[i])) return true;
          if (++i == items.length) i = 0;
        } while (i != putIndex);
      }
      return false;
    } finally {
      lock.unlock();
    }
  }
  
  public Object[] toArray() {
    Object[] a;
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      final int count = this.count;
      a = new Object[count];
      int n = items.length - takeIndex;
      if (count <= n) {
        System.arraycopy(items, takeIndex, a, 0, count);
      } else {
        System.arraycopy(items, takeIndex, a, 0, n);
        System.arraycopy(items, 0, a, n, count - n);
      }
    } finally {
      lock.unlock();
    }
    return a;
  }

  /**
   * Atomically removes all of the elements from this queue.
   * The queue will be empty after this call returns.
   */
  public void clear() {
    final Object[]  items = this.items;
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
      int k = count;
      if (k > 0) {
        final int putIndex = this.putIndex;
        int i = takeIndex;
        do {
          items[i] = null;
          if (++i == items.length)
            i = 0;
        } while (i != putIndex);
        takeIndex = putIndex;
        count = 0;
        for (; k > 0 && lock.hasWaiters(notFull); k--)
          notFull.signal();
      }
    } finally {
      lock.unlock();
    }
  }
}
