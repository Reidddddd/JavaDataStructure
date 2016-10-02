package train.tcrn.c29;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamDemo5 {
  public static void main(String[] args) {
    ArrayList<NamePhoneEmail> myList = new ArrayList<>();
    myList.add(new NamePhoneEmail("larry", "555-5555", "larry@herbschildt.com"));
    myList.add(new NamePhoneEmail("james", "555-4444", "james@herbschildt.com"));
    myList.add(new NamePhoneEmail("mary", "555-3333", "mary@herbschildt.com"));
    System.out.println("Original values in myList: ");
    myList.stream().forEach(a -> {
      System.out.println(a.name + " " + a.phonenum + " " + a.email);
    });
    System.out.println();
    Stream<NamePhone> nameAndPhone = myList.stream().map(a -> new NamePhone(a.name, a.phonenum));
    System.out.println("List of names and phone numbers: ");
    nameAndPhone.forEach(a -> {
      System.out.println(a.name + " " + a.phonenum);
    });
  }
}

class NamePhoneEmail {
  String name;
  String phonenum;
  String email;

  public NamePhoneEmail(String n, String p, String e) {
    name = n;
    phonenum = p;
    email = e;
  }
}

class NamePhone {
  String name;
  String phonenum;

  public NamePhone(String n, String p) {
    name = n;
    phonenum = p;
  }
}