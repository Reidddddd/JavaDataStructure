package train.tcrn.c17;

public class RTTI {
  public static void main(String[] args) {
    X x = new X();
    Y y = new Y();
    Class<?> obj;
    obj = x.getClass();
    System.out.println("x is object of type: " + obj.getName());
    obj = y.getClass();
    System.out.println("y is object of type: " + obj.getName());
    obj = obj.getSuperclass();
    System.out.println("y's superclass is " + obj.getName());
  }
}

class X {
  int a;
  float b;
}

class Y extends X {
  double c;
}
