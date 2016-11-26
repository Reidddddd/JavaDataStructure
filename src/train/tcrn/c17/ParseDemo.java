package train.tcrn.c17;

public class ParseDemo {
  public static void main(String[] args) {
    String[] strs = { "1", "123", "45", "78", "90", "61", "56" };
    int sum = 0;
    for (int i = 0; i < strs.length; i++) {
      sum += i;
    }
    System.out.println("Sum is: " + sum);
  }
}
