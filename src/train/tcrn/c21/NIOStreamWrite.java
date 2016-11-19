package train.tcrn.c21;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NIOStreamWrite {
  private static String PATH = "nio stream write.txt";

  public static void main(String[] args) {
    try (OutputStream os = new BufferedOutputStream(Files.newOutputStream(Paths.get(PATH)))) {
      for (int i = 0; i < 26; i++) {
        os.write((byte) ('a' + i));
      }
    } catch (IOException e) {
    }
  }
}
