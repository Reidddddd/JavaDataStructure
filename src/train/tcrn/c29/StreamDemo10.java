package train.tcrn.c29;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.stream.Stream;

public class StreamDemo10 {
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
    Spliterator<String> splitItr2 = splitItr.trySplit();
    if (splitItr2 != null) {
      System.out.println("Output from splitItr2");
      splitItr2.forEachRemaining(n -> System.out.println(n));
    }
    System.out.println("\nOutput from splitItr:");
    splitItr.forEachRemaining(n -> System.out.println(n));
  }
}
