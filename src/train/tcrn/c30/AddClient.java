package train.tcrn.c30;

import java.rmi.Naming;

public class AddClient {
  public static void main(String[] args) throws Exception {
    String addServer = "rmi://127.0.0.1/Addserver";
    AddServerIntf addServerIntf = (AddServerIntf) Naming.lookup(addServer);
    System.out.println("The sum is: " + addServerIntf.add(1.0, 2.0));
  }
}
