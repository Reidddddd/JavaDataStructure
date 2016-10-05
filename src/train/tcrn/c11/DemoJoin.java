package train.tcrn.c11;

public class DemoJoin {
  public static void main(String[] args) {
    NewThread3 nt1 = new NewThread3("One");
    NewThread3 nt2 = new NewThread3("Two");
    NewThread3 nt3 = new NewThread3("Three");
    System.out.println("Thread one is alive: " + nt1.t.isAlive());
    System.out.println("Thread two is alive: " + nt2.t.isAlive());
    System.out.println("Thread three is alive: " + nt3.t.isAlive());

    try {
      System.out.println("Waiting for threads to finish.");
      nt1.t.join();
      nt2.t.join();
      nt3.t.join();
    } catch (Exception e) {
      System.out.println("Main thread Interrupted");
    }
    System.out.println("Thread one is alive: " + nt1.t.isAlive());
    System.out.println("Thread two is alive: " + nt2.t.isAlive());
    System.out.println("Thread three is alive: " + nt3.t.isAlive());
    System.out.println("Main thread exiting.");
  }
}
