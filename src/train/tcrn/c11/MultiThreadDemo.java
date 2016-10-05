package train.tcrn.c11;

public class MultiThreadDemo {
  public static void main(String[] args) {
    new NewThread3("One");
    new NewThread3("Two");
    new NewThread3("Three");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      System.out.println("Main thread interrupted.");
    }
    System.out.println("Main thread exiting");
  }
}

class NewThread3 implements Runnable {
  Thread t;
  String name;

  public NewThread3(String threadName) {
    name = threadName;
    t = new Thread(this, threadName);
    System.out.println("New thread: " + t);
    t.start();
  }

  @Override
  public void run() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println(name + ": " + i);
        Thread.sleep(2000);
      }
    } catch (Exception e) {
      System.out.println(name + " Interrupted.");
    }
    System.out.println(name + " exiting.");
  }
}