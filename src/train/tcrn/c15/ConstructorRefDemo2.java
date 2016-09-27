package train.tcrn.c15;

public class ConstructorRefDemo2 {
  public static void main(String[] args) {
    MyFunc5<Integer> myClassCons = MyClass2<Integer>::new;
    MyClass2<Integer> mc = myClassCons.func(100);
    System.out.println("val in mc is " + mc.getVal());
  }
}

class MyClass2<T> {
  private T val;

  MyClass2() { val = null; }
  MyClass2(T val) { this.val = val; }
  T getVal() { return val; }
}

interface MyFunc5<T> {
  MyClass2<T> func(int n);
}