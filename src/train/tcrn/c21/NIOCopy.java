package train.tcrn.c21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NIOCopy {
  static String PATH = "mapped channel write.txt";
  static String COPY = "copy.txt";

  public static void main(String[] args) {
    try {
      Path source = Paths.get(PATH);
      Path target = Paths.get(COPY);
      Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
    }
  }
}
