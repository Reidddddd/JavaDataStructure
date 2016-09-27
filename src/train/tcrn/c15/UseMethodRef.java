package train.tcrn.c15;

import java.util.ArrayList;
import java.util.Collections;

public class UseMethodRef {
  static int compare(MyClass a, MyClass b) {
    return a.getVal() - b.getVal();
  }

  public static void main(String[] args) {
    ArrayList<MyClass> al = new ArrayList<>();
    al.add(new MyClass(1));
    al.add(new MyClass(4));
    al.add(new MyClass(2));
    al.add(new MyClass(9));
    al.add(new MyClass(3));
    al.add(new MyClass(7));
    MyClass max = Collections.max(al, UseMethodRef::compare);
    System.out.println("Maximum value is: " + max.getVal());
  }
}

class MyClass {
  private int val;

  MyClass() { val = 0; }
  MyClass(int val) { this.val = val; }
  int getVal() { return val; }
}
