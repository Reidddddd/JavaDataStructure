package train.tcrn.c30;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpr {
  public static void main(String[] args) {
    Pattern pat = Pattern.compile("Java");
    Matcher mat = pat.matcher("Java");
    System.out.println("Testing Java against Java.");
    if (mat.matches()) System.out.println("Matches");
    else System.out.println("No Match");
    System.out.println();
    System.out.println("Test Java against Java 8.");
    mat = pat.matcher("Java 8");
    if (mat.matches()) System.out.println("Matches");
    else System.out.println("No Match");
  }
}
