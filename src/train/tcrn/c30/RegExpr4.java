package train.tcrn.c30;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpr4 {
  public static void main(String[] args) {
    Pattern pat = Pattern.compile("W+");
    Matcher mat = pat.matcher("W WW WWW");
    while (mat.find()) {
      System.out.println("test found at index " + mat.group());
    }
  }
}
