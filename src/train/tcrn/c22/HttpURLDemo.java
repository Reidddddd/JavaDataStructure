package train.tcrn.c22;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpURLDemo {
  public static void main(String[] args) throws IOException {
    URL hp = new URL("http://www.immomo.com");
    HttpURLConnection con = (HttpURLConnection) hp.openConnection();
    System.out.println("Request method is " + con.getRequestMethod());
    System.out.println("Response code is " + con.getResponseCode());
    System.out.println("Response message is " + con.getResponseMessage());
    Map<String, List<String>> hdrMap = con.getHeaderFields();
    Set<String> hdrField = hdrMap.keySet();
    System.out.println("\nHere is the header:");
    for (String k : hdrField) {
      System.out.println("Key: " + k + " Value: " + hdrMap.get(k));
    }
  }
}
