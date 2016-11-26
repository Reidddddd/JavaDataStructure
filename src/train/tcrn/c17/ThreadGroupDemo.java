package train.tcrn.c17;

public class ThreadGroupDemo {
  public static void main(String[] args) {
    ThreadGroup ga = new ThreadGroup("Group A");
    ThreadGroup gb = new ThreadGroup("Group B");
    NewThread nt1 = new NewThread("One", ga);
    NewThread nt2 = new NewThread("Two", ga);
    NewThread nt3 = new NewThread("Three", gb);
    NewThread nt4 = new NewThread("Four", gb);
    System.out.println("\nHere is output from list():");
    ga.list();
    gb.list();
    System.out.println();
    System.out.println("Suspending Group A");
    Thread tga[] = new Thread[ga.activeCount()];
    ga.enumerate(tga);
    for (int i = 0; i < tga.length; i++) {
      ((NewThread) tga[i]).mysuspend();
    }
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      System.out.println("Main thread interrupted.");
    }
    System.out.println("Resuming Group A");
    for (int i = 0; i < tga.length; i++) {
      ((NewThread) tga[i]).myresume();
    }
    System.out.println("Waiting for threads to finish");
    try {
      nt1.join();
      nt2.join();
      nt3.join();
      nt4.join();
    } catch (InterruptedException e) {
      System.out.println("Exception in Main thread");
    }
    System.out.println("Main thread exiting");
  }
}

class NewThread extends Thread {
  boolean suspendFlag;

  public NewThread(String threadName, ThreadGroup tgOb) {
    super(tgOb, threadName);
    System.out.println("New thread: " + this);
    suspendFlag = false;
    start();
  }

  @Override
  public void run() {
    for (int i = 5; i > 0; i--) {
      System.out.println(getName() + ": " + i);
      try {
        Thread.sleep(10000);
        synchronized (this) {
          while (suspendFlag) {
            wait();
          }
        }
      } catch (InterruptedException e) {
      }
    }
    System.out.println(getName() + " exiting.");
  }

  synchronized void mysuspend() {
    suspendFlag = true;
  }

  synchronized void myresume() {
    suspendFlag = false;
    notify();
  }
}