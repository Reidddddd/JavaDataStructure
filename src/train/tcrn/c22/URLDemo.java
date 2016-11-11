package train.tcrn.c22;

import java.io.IOException;
import java.net.URL;

public class URLDemo {
  public static void main(String[] args) throws IOException {
    URL hp = new URL("http://www.immomo.com/jobs/join/index");
    System.out.println("Protocol: " + hp.getProtocol());
    System.out.println("Port: " + hp.getPort());
    System.out.println("Host: " + hp.getHost());
    System.out.println("File: " + hp.getFile());
    System.out.println("Ext: " + hp.toExternalForm());
  }
}
