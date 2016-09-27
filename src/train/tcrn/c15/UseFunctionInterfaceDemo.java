package train.tcrn.c15;

import java.util.function.Function;

public class UseFunctionInterfaceDemo {
  public static void main(String[] args) {
    Function<Integer, Integer> factorial = n -> {
      int result = 1;
      for (int i = 2; i <= n; i++) result *= i;
      return result;
    };
    System.out.println("The factorial of 3 is " + factorial.apply(3));
    System.out.println("The factorial of 5 is " + factorial.apply(5));
  }
}
