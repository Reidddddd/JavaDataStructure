package train.tcrn.c15;

public class MethodRefDemo2 {
  static String stringOps(StringFunc sf, String s) {
    return sf.func(s);
  }

  public static void main(String[] args) {
    String inStr = "Lambdas add power to Java";
    String outStr;
    MyStringOps ops = new MyStringOps();
    outStr = stringOps(ops::reverseStr, inStr);
    System.out.println("Original string: " + inStr);
    System.out.println("String reversed: " + outStr);
  }
}