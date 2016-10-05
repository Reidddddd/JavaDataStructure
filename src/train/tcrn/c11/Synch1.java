package train.tcrn.c11;

public class Synch1 {
  public static void main(String[] args) {
    Callme target = new Callme();
    Caller2 ob1 = new Caller2(target, "Hello");
    Caller2 ob2 = new Caller2(target, "Synchronized");
    Caller2 ob3 = new Caller2(target, "World");
    try {
      ob1.t.join();
      ob2.t.join();
      ob3.t.join();
    } catch (InterruptedException e) {
      System.out.println("Interrupted");
    }
  }
}

class Caller2 implements Runnable {
  String msg;
  Callme targe;
  Thread t;

  public Caller2(Callme targ, String s) {
    this.targe = targ;
    this.msg = s;
    t = new Thread(this);
    t.start();
  }

  @Override
  public void run() {
    synchronized (targe) {
      targe.call(msg);
    }
  }
}