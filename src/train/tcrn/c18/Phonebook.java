package train.tcrn.c18;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Phonebook {
  public static void main(String[] args) throws IOException {
    Properties ht = new Properties();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String name, number;
    FileInputStream fin = new FileInputStream("phonebook.dat");
    boolean changed = false;
    do {
      System.out.println("Enter new name" + " ('quit' to stop):");
      name = br.readLine();
      if (name.equals("quit")) continue;
      System.out.println("Enter number: ");
      number = br.readLine();
      ht.put(name, number);
      changed = true;
    } while (!name.equals("quit"));
    if (changed) {
      FileOutputStream fout = new FileOutputStream("phonebook.dat");
      ht.store(fout, "Telephone Book");
      fout.close();
    }
    do {
      System.out.println("Enter name to find ('quit' to quit): ");
      name = br.readLine();
      if (name.equals("quit")) continue;
      number = (String) ht.get(name);
      System.out.println(number);
    } while (!name.equals("quit"));
  }
}
