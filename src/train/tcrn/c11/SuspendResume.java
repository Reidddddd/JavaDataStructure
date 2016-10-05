package train.tcrn.c11;

public class SuspendResume {
  public static void main(String[] args) {
    NewThread4 ob1 = new NewThread4("One");
    NewThread4 ob2 = new NewThread4("Two");
    try {
      Thread.sleep(1000);
      ob1.mysuspend();
      System.out.println("Suspending thread one");
      Thread.sleep(10000);
      ob1.myresume();
      System.out.println("Resuming thread one");
      ob2.mysuspend();
      System.out.println("Suspending thread two");
      ob2.myresume();
      System.out.println("Resuming thread two");
    } catch (InterruptedException e) {
      System.out.println("Main thread Interrupted");
    }

    try {
      System.out.println("Waiting for threads to finish.");
      ob1.t.join();
      ob2.t.join();
    } catch (InterruptedException e) {
      System.out.println("Main thread Interrupted");
    }
    System.out.println("Main thread exiting.");
  }
}

class NewThread4 implements Runnable {
  Thread t;
  String name;
  boolean suspendFlag;

  public NewThread4(String threadName) {
    name = threadName;
    t = new Thread(this, threadName);
    System.out.println("New thread: " + t);
    suspendFlag = false;
    t.start();
  }

  @Override
  public void run() {
    try {
      for (int i = 15; i > 0; i--) {
        System.out.println(name + ": " + i);
        Thread.sleep(1000);
        synchronized (this) {
          while (suspendFlag) {
            wait();
          }
        }
      }
    } catch (Exception e) {
      System.out.println(name + " Interrupted.");
    }
    System.out.println(name + " exiting.");
  }

  synchronized void mysuspend() {
    suspendFlag = true;
  }

  synchronized void myresume() {
    suspendFlag = false;
    notify();
  }
}