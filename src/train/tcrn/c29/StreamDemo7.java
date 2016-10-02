package train.tcrn.c29;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo7 {
  public static void main(String[] args) {
    ArrayList<NamePhoneEmail> myList = new ArrayList<>();
    myList.add(new NamePhoneEmail("larry", "555-5555", "larry@herbschildt.com"));
    myList.add(new NamePhoneEmail("james", "555-4444", "james@herbschildt.com"));
    myList.add(new NamePhoneEmail("mary", "555-3333", "mary@herbschildt.com"));
    Stream<NamePhone> nameAndPhone = myList.stream().map(a -> new NamePhone(a.name, a.phonenum));
    List<NamePhone> npList = nameAndPhone.collect(Collectors.toList());
    System.out.println("Names and phone numbers in a List:");
    for (NamePhone e : npList) System.out.println(e.name + ": " + e.phonenum);
    nameAndPhone = myList.stream().map(a -> new NamePhone(a.name, a.phonenum));
    Set<NamePhone> npSet = nameAndPhone.collect(Collectors.toSet());
    System.out.println("\nNames and phone numbers in a Set:");
    for (NamePhone e : npSet) System.out.println(e.name + ": " + e.phonenum);
  }
}
