package train.tcrn.c30;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeDemo {
  public static void main(String[] args) {
    LocalDate curDate = LocalDate.now();
    System.out.println(curDate);
    LocalTime curTime = LocalTime.now();
    System.out.println(curTime);
    LocalDateTime curDateTime = LocalDateTime.now();
    System.out.println(curDateTime);
    System.out.println(curDateTime.toLocalDate());
    System.out.println(curDateTime.toLocalTime());
  }
}
