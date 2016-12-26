package train.tcrn.c19;

import java.util.Date;

public class DateDemo {
  public static void main(String[] args) {
    Date date = new Date();
    System.out.println(date);
    long msec = date.getTime();
    System.out.println("Milliseconds since Jan. 1, 1970, GMT = " + msec);
  }
}
