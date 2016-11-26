package train.tcrn.c17;

import java.io.IOException;

public class ExecDemoFini {
  public static void main(String[] args) throws IOException, InterruptedException {
    Runtime r = Runtime.getRuntime();
    Process p = r.exec("notepad");
    p.waitFor();
    System.out.println("Exit value: " + p.exitValue());
  }
}
