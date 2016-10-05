package train.tcrn.c11;

public class Synch {
  public static void main(String[] args) {
    Callme target = new Callme();
    Caller ob1 = new Caller(target, "Hello");
    Caller ob2 = new Caller(target, "Synchronized");
    Caller ob3 = new Caller(target, "World");
    try {
      ob1.t.join();
      ob2.t.join();
      ob3.t.join();
    } catch (InterruptedException e) {
      System.out.println("Interrupted");
    }
  }
}

class Callme {
    /** synchronized */ void call(String msg) {
    System.out.print("[" + msg);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      System.out.println("Interrupted");
    }
    System.out.println("]");
  }
}

class Caller implements Runnable {
  String msg;
  Callme targe;
  Thread t;

  public Caller(Callme targ, String s) {
    this.targe = targ;
    this.msg = s;
    t = new Thread(this);
    t.start();
  }

  @Override
  public void run() {
    targe.call(msg);
  }
}