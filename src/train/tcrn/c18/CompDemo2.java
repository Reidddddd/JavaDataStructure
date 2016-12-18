package train.tcrn.c18;

import java.util.TreeSet;

public class CompDemo2 {
  public static void main(String[] args) {
    TreeSet<String> ts = new TreeSet<>((astr, bstr) -> bstr.compareTo(astr));
    ts.add("C");
    ts.add("A");
    ts.add("B");
    ts.add("E");
    ts.add("F");
    ts.add("D");
    for (String element : ts) System.out.print(element + " ");
    System.out.println();
  }
}
