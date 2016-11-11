package train.tcrn.c22;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Whois {
  public static void main(String[] args) throws UnknownHostException, IOException {
    int c;
    Socket s = new Socket("whois.internic.net", 43);
    InputStream is = s.getInputStream();
    OutputStream out = s.getOutputStream();

    String str = args.length == 0 ? "momo.com" : args[0] + "\n";
    byte[] buf = str.getBytes();
    out.write(buf);
    while ((c = is.read()) != -1) {
      System.out.println((char) c);
    }
    s.close();
  }
}
