package train.tcrn.c21;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DirTreeList {
  public static void main(String[] args) {
    String dir = "D:\\development\\workspace";
    System.out.println("Directory tree starting with " + dir + ":\n");
    try {
      Files.walkFileTree(Paths.get(dir), new MyFileVisitor());
    } catch (IOException e) {
    }
  }
}

class MyFileVisitor extends SimpleFileVisitor<Path> {
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    System.out.println(file);
    return FileVisitResult.CONTINUE;
  }
}