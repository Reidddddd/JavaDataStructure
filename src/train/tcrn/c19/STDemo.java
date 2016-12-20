package train.tcrn.c19;

import java.util.StringTokenizer;

public class STDemo {
  static String in = "title=Java: The Complete Reference;" + "author=Schildt;"
      + "pubulisher=McGraw-Hill;" + "copyright=2014";

  public static void main(String[] args) {
    StringTokenizer st = new StringTokenizer(in, "=;");
    while (st.hasMoreTokens()) {
      String key = st.nextToken();
      String val = st.nextToken();
      System.out.println(key + "\t" + val);
    }
  }
}
