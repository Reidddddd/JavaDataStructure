package train.tcrn.c19;

import java.util.Calendar;
import java.util.Formatter;

public class FormatDemo6 {
  public static void main(String[] args) {
    Formatter fmt = new Formatter();
    Calendar cal = Calendar.getInstance();
    fmt.format("Today is day %te of %<tB, %<tY", cal);
    System.out.println(fmt);
    fmt.close();
  }
}
