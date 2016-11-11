package train.tcrn.c22;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
  public static void main(String[] args) throws UnknownHostException {
    InetAddress address = InetAddress.getLocalHost();
    System.out.println(address);
    address = InetAddress.getByName("www.momo.com");
    System.out.println(address);
    InetAddress[] sw = InetAddress.getAllByName("www.nba.com");
    for (int i = 0; i < sw.length; i++) {
      System.out.println(sw[i]);
    }
  }
}
