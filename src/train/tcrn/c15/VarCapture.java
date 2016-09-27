package train.tcrn.c15;

public class VarCapture {
  public static void main(String[] args) {
    int num = 10;
    MyFunc myLambda = n -> {
      int v = num + n;
      // num++;
      return v;
    };
    System.out.println("Var capture: " + myLambda.func(1));
    // num = 9;
  }
}

interface MyFunc {
  int func(int n);
}