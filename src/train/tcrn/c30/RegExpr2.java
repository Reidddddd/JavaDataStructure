package train.tcrn.c30;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpr2 {
  public static void main(String[] args) {
    Pattern pat = Pattern.compile("Java");
    Matcher mat = pat.matcher("Java");
    System.out.println("Looking for Java in Java 8.");
    if (mat.find()) System.out.println("subsequence found");
    else System.out.println("No Match");
  }
}
