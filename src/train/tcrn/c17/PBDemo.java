package train.tcrn.c17;

import java.io.IOException;

public class PBDemo {
  public static void main(String[] args) throws IOException {
    ProcessBuilder proc = new ProcessBuilder("notepad.exe", "testfile");
    proc.start();
  }
}
