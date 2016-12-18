package train.tcrn.c18;

import java.util.Enumeration;
import java.util.Hashtable;

public class HTDemo {
  public static void main(String[] args) {
    Hashtable<String, Double> balance = new Hashtable<>();
    balance.put("John Doe", new Double(3434.34));
    balance.put("Tom Smith", new Double(123.22));
    balance.put("Jane Baker", new Double(1378.00));
    balance.put("Tod Hall", new Double(99.22));
    balance.put("Ralph Smith", new Double(-19.08));
    String str;
    Enumeration<String> names = balance.keys();
    while (names.hasMoreElements()) {
      str = names.nextElement();
      System.out.println(str + ": " + balance.get(str));
    }
    System.out.println();
    double bal;
    bal = balance.get("John Doe");
    balance.put("John Doe", bal + 1000);
    System.out.println("John Doe's new balance: " + balance.get("John Doe"));
  }
}
