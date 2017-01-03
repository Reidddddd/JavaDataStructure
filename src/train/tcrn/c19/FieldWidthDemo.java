package train.tcrn.c19;

import java.util.Formatter;

public class FieldWidthDemo {
  public static void main(String[] args) {
    Formatter fmt;
    for (int i = 1; i <= 10; i++) {
      fmt = new Formatter();
      fmt.format("%4d %4d %4d", i, i * i, i * i * i);
      System.out.println(fmt);
      fmt.close();
    }
  }
}
