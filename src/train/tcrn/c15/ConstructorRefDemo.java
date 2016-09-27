package train.tcrn.c15;

public class ConstructorRefDemo {
  public static void main(String[] args) {
    MyFunc4 myClassCons = MyClass::new;
    MyClass mc = myClassCons.func(100);
    System.out.println("val is mc is " + mc.getVal());
  }
}

interface MyFunc4 {
  MyClass func(int n);
}
