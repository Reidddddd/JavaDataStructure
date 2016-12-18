package train.tcrn.c18;

import java.util.Comparator;
import java.util.TreeSet;

public class CompDemo {
  public static void main(String[] args) {
    TreeSet<String> ts = new TreeSet<>(new MyComp());
    ts.add("C");
    ts.add("A");
    ts.add("B");
    ts.add("E");
    ts.add("F");
    ts.add("D");
    for (String element : ts) System.out.print(element + " ");
    System.out.println();

    TreeSet<String> ts1 = new TreeSet<>(new MyComp().reversed());
    ts1.add("C");
    ts1.add("A");
    ts1.add("B");
    ts1.add("E");
    ts1.add("F");
    ts1.add("D");
    for (String element : ts1) System.out.print(element + " ");
    System.out.println();
  }
}

class MyComp implements Comparator<String> {
  @Override
  public int compare(String o1, String o2) {
    return o2.compareTo(o1);
  }
}