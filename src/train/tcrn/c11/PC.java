package train.tcrn.c11;

public class PC {
  public static void main(String[] args) {
    Q q = new Q();
    new Producer(q);
    new Consumer(q);
    System.out.println("Press Control-C to stop.");
  }
}

class Q {
  int n;
  boolean valueSet = false;

  synchronized int get() {
    while (!valueSet) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("Interrupted Exception caught");
      }
    }
    System.out.println("Got: " + n);
    valueSet = false;
    notify();
    return n;
  }

  synchronized void put(int n) {
    while (valueSet) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("Interrupted Exception caught");
      }
    }
    this.n = n;
    valueSet = true;
    System.out.println("Put: " + n);
    notify();
  }
}

class Producer implements Runnable {
  Q q;

  Producer(Q q) {
    this.q = q;
    new Thread(this, "Producer").start();
  }

  @Override
  public void run() {
    int i = 0;
    while (true) {
      q.put(i++);
    }
  }
}

class Consumer implements Runnable {
  Q q;

  Consumer(Q q) {
    this.q = q;
    new Thread(this, "Consumer").start();
  }

  @Override
  public void run() {
    while (true)
      q.get();
  }
}