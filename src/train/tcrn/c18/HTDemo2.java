package train.tcrn.c18;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class HTDemo2 {
  public static void main(String[] args) {
    Hashtable<String, Double> balance = new Hashtable<>();
    balance.put("John Doe", new Double(3434.34));
    balance.put("Tom Smith", new Double(123.22));
    balance.put("Jane Baker", new Double(1378.00));
    balance.put("Tod Hall", new Double(99.22));
    balance.put("Ralph Smith", new Double(-19.08));
    String str;
    Set<String> set = balance.keySet();
    Iterator<String> itr = set.iterator();
    while (itr.hasNext()) {
      str = itr.next();
      System.out.println(str + ": " + balance.get(str));
    }
    System.out.println();
    double bal;
    bal = balance.get("John Doe");
    balance.put("John Doe", bal + 1000);
    System.out.println("John Doe's new balance: " + balance.get("John Doe"));
  }

}
