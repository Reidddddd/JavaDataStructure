package train.tcrn.c29;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class StreamDemo8 {
  public static void main(String[] args) {
    ArrayList<String> mylist = new ArrayList<>();
    mylist.add("Alpha");
    mylist.add("Beta");
    mylist.add("Gamma");
    mylist.add("Delta");
    mylist.add("Phi");
    mylist.add("Omega");
    Stream<String> myStream = mylist.stream();
    Iterator<String> itr = myStream.iterator();
    while (itr.hasNext())
      System.out.println(itr.next());
  }
}
