package train.tcrn.c19;

import java.util.Timer;
import java.util.TimerTask;

public class TTest {
  public static void main(String[] args) {
    MyTimerTask myTask = new MyTimerTask();
    Timer myTimer = new Timer();
    myTimer.schedule(myTask, 10000, 2000);
    try {
      Thread.sleep(50000);
    } catch (InterruptedException e) {
    }
    myTimer.cancel();
  }
}

class MyTimerTask extends TimerTask {
  @Override
  public void run() {
    System.out.println("Timer task executed.");
  }
}