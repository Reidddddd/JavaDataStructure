package train.tcrn.c19;

import java.util.Currency;
import java.util.Locale;

public class CurDemo {
  public static void main(String[] args) {
    Currency c = Currency.getInstance(Locale.US);
    System.out.println("Symbol: " + c.getSymbol());
    System.out.println("Default fractional digits: " + c.getDefaultFractionDigits());
  }
}
