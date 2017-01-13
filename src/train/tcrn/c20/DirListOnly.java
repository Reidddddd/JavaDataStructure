package train.tcrn.c20;

import java.io.File;
import java.io.FilenameFilter;

public class DirListOnly {
  public static void main(String[] args) {
    String dirname = "D:\\development\\workspace\\java-training";
    File f1 = new File(dirname);
    FilenameFilter only = new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        File f = new File(dir + "\\" + name);
        return f.isDirectory();
      }
    };
    String[] s = f1.list(only);
    for (int i = 0; i < s.length; i++) {
      System.out.println(s[i]);
    }
  }
}
