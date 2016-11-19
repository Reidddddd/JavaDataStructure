package train.tcrn.c21;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShowFile {
  private static final String PATH =
      "D:\\development\\workspace\\java-training\\src\\train\\tcrn\\c11\\CurrentThreadDemo.java";

  public static void main(String[] args) {
    int i = 0;
    try (InputStream is = Files.newInputStream(Paths.get(PATH))) {
      do {
        i = is.read();
        if (i != -1) System.out.print((char) i);
      } while (i != -1);
    } catch (IOException e) {
    }
  }
}
