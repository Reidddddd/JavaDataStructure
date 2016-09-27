package train.tcrn.c15;

public class BlockLambdaDemo {
  public static void main(String[] args) {
    NumericFunc factorial = n -> {
      int result = 1;
      for (int i = 1; i <= n; i++) result *= i;
      return result;
    };
    System.out.println("The factorial of 3 is " + factorial.func(3));
    System.out.println("The factorial of 5 is " + factorial.func(5));
  }
}

interface NumericFunc {
  int func(int n);
}
