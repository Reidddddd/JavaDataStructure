package train.tcrn.c17;

import java.io.IOException;

public class ExecDemo {
  public static void main(String[] args) {
    Runtime r = Runtime.getRuntime();
    try {
      Process p = r.exec("notepad");
    } catch (IOException e) {
      System.out.println("Error executing");
    }
  }
}
