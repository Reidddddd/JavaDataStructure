package train.tcrn.c29;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.stream.Stream;

public class StreamDemo9 {
  public static void main(String[] args) {
    ArrayList<String> mylist = new ArrayList<>();
    mylist.add("Alpha");
    mylist.add("Beta");
    mylist.add("Gamma");
    mylist.add("Delta");
    mylist.add("Phi");
    mylist.add("Omega");
    Stream<String> myStream = mylist.stream();
    Spliterator<String> splitItr = myStream.spliterator();
    while (splitItr.tryAdvance(n -> System.out.println(n.toUpperCase())));
  }
}
