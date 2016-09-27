package train.tcrn.c15;

public class ConstructorRefDemo3 {
  static <R, T> R myClassFactory(MyFunc6<R, T> cons, T v) {
    return cons.func(v);
  }

  public static void main(String[] args) {
    MyFunc6<MyClass2<Double>, Double> myClassCons = MyClass2<Double>::new;
    MyClass2<Double> mc = myClassFactory(myClassCons, 100.1);
    System.out.println("val in mc is " + mc.getVal());
    MyFunc6<MyClass3, String> myClassCons2 = MyClass3::new;
    MyClass3 mc3 = myClassFactory(myClassCons2, "Lambda");
    System.out.println("str in mc3 is " + mc3.getVal());
  }
}

class MyClass3 {
  private String val;

  MyClass3() { val = ""; }
  MyClass3(String val) { this.val = val; }
  String getVal() { return val; }
}

interface MyFunc6<R, T> {
  R func(T n);
}