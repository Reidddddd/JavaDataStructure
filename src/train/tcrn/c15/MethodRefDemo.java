package train.tcrn.c15;

public class MethodRefDemo {
  static String stringOps(StringFunc sf, String s) {
    return sf.func(s);
  }

  public static void main(String[] args) {
    String inStr = "Lambdas add power to Java";
    String outStr;
    outStr = stringOps(MyStringOps::strReverse, inStr);
    System.out.println("Original string: " + inStr);
    System.out.println("String reversed: " + outStr);
  }
}

class MyStringOps {
  static String strReverse(String str) {
    String result = "";
    for (int i = str.length() - 1; i >= 0; i--) result += str.charAt(i);
    return result;
  }

  String reverseStr(String str) {
    String result = "";
    for (int i = str.length() - 1; i >= 0; i--) result += str.charAt(i);
    return result;
  }
}