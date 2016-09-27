package train.tcrn.c15;

public class LambdaAsArgumentsDemo {
  static String stringOp(StringFunc2 sf, String s) {
    return sf.func(s);
  }

  public static void main(String[] args) {
    String inStr = "Lambda add power to Java";
    String outStr;
    System.out.println("Here is input string: " + inStr);

    outStr = stringOp(str -> str.toUpperCase(), inStr);
    System.out.println("The string in uppercase: " + outStr);
    outStr = stringOp(str -> {
      String result = "";
      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) != ' ') result += str.charAt(i);
      }
      return result;
    }, inStr);
    System.out.println("The string with spaces removed: " + outStr);

    StringFunc2 reverse = str -> {
      String result = "";
      for (int i = str.length() - 1; i >= 0; i--) result += str.charAt(i);
      return result;
    };
    System.out.println("The string reversed: " + stringOp(reverse, inStr));
  }
}

interface StringFunc2 {
  String func(String s);
}
