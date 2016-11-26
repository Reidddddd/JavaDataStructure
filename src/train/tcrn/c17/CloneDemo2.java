package train.tcrn.c17;

public class CloneDemo2 {
  public static void main(String[] args) throws CloneNotSupportedException {
    TestClone2 x1 = new TestClone2();
    TestClone2 x2;
    x1.a = 10;
    x1.b = 20.98;
    x2 = (TestClone2) x1.clone();
    System.out.println("x1: " + x1.a + " " + x1.b);
    System.out.println("x2: " + x2.a + " " + x2.b);
    x2.a = 1;
    x2.b = 100.1;
    System.out.println("x1: " + x1.a + " " + x1.b);
    System.out.println("x2: " + x2.a + " " + x2.b);
  }
}

class TestClone2 implements Cloneable {
  int a;
  double b;

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}