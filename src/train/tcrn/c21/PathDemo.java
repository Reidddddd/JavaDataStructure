package train.tcrn.c21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class PathDemo {
  private static final String PATH =
      "D:\\development\\workspace\\java-training\\src\\train\\tcrn\\c11\\CurrentThreadDemo.java";

  public static void main(String[] args) {
    Path filePath = Paths.get(PATH);
    System.out.println("File Name: " + filePath.getName(filePath.getNameCount() - 1));
    System.out.println("Path: " + filePath);
    System.out.println("Absolute Path: " + filePath.toAbsolutePath());
    System.out.println("Parent: " + filePath.getParent());
    if (Files.exists(filePath)) {
      System.out.println("File exists");
    } else {
      System.out.println("File does not exist");
    }
    try {
      if (Files.isHidden(filePath)) {
        System.out.println("File is hidden");
      } else {
        System.out.println("File is not hidden");
      }
    } catch (IOException e) {
    }
    if (Files.isWritable(filePath)) System.out.println("File is writable");
    if (Files.isReadable(filePath)) System.out.println("File is readable");
    try {
      BasicFileAttributes attribs = Files.readAttributes(filePath, BasicFileAttributes.class);
      if (attribs.isDirectory()) System.out.println("It is a directory");
      else System.out.println("It is not a directory");
      if (attribs.isRegularFile()) System.out.println("The file is a normal file");
      else System.out.println("The file is not a normal file");
      if (attribs.isSymbolicLink()) System.out.println("The file is symbolic link");
      else System.out.println("The file is not a symbolic link");
      System.out.println("File last modified: " + attribs.lastModifiedTime());
      System.out.println("File size: " + attribs.size() + " Bytes.");
    } catch (IOException e) {
    }
  }
}
