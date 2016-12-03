package train.tcrn.c18;

import java.util.TreeSet;

public class TreeSetDemo {
  public static void main(String[] args) {
    TreeSet<String> ts = new TreeSet<>();
    ts.add("C");
    ts.add("B");
    ts.add("A");
    ts.add("E");
    ts.add("F");
    ts.add("D");
    System.out.println(ts);
    System.out.println(ts.lower("E"));
    System.out.println(ts.higher("E"));
    System.out.println(ts.headSet("E", true));
  }
}
