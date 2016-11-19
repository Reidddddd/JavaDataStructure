package train.tcrn.c21;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class DirList {
  public static void main(String[] args) {
    String dir = "D:\\development";
    try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(dir))) {
      System.out.println("Directory of " + dir);
      for (Path entry : dirStream) {
        BasicFileAttributes attribs = Files.readAttributes(entry, BasicFileAttributes.class);
        if (attribs.isDirectory()) System.out.print("<DIR> ");
        else System.out.print("         ");
        System.out.println(entry.getName(1));
      }
    } catch (IOException e) {
    }
  }
}
