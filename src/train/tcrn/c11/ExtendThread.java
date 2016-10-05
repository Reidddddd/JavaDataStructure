package train.tcrn.c11;

public class ExtendThread {
  public static void main(String[] args) {
    new NewThread2();
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println("Main Thread: " + i);
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      System.out.println("Main thread interrupted.");
    }
    System.out.println("Main thread exiting");
  }
}

class NewThread2 extends Thread {
  public NewThread2() {
    super("Demo Thread");
    System.out.println("Child thread: " + this);
    start();
  }

  @Override
  public void run() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println("Child Thread: " + i);
        Thread.sleep(5000);
      }
    } catch (Exception e) {
      System.out.println("Child interrupted.");
    }
    System.out.println("Exiting child thread.");
  }
}