package train.tcrn.c19;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class LRBDemo {
  public static void main(String[] args) {
    ResourceBundle rd = ResourceBundle.getBundle("SampleRB_en", Locale.ENGLISH);
    System.out.println("English version: ");
    System.out.println("String for Title key: " + rd.getString("title"));
    System.out.println("String for StopText key: " + rd.getString("StopText"));
    System.out.println("String for StartText key: " + rd.getString("StartText"));

    rd = ResourceBundle.getBundle("SampleRB_de", Locale.GERMAN);
    System.out.println("\nGerman version: ");
    System.out.println("String for Title key: " + rd.getString("title"));
    System.out.println("String for StopText key: " + rd.getString("StopText"));
    System.out.println("String for StartText key: " + rd.getString("StartText"));
  }
}

class SampleRB_en extends ListResourceBundle {
  @Override
  protected Object[][] getContents() {
    Object[][] resources = new Object[3][2];
    resources[0][0] = "title";
    resources[0][1] = "My Program";
    resources[1][0] = "StopText";
    resources[1][1] = "Stop";
    resources[2][0] = "StartText";
    resources[2][1] = "Start";
    return resources;
  }
}

class SampleRB_de extends ListResourceBundle {
  @Override
  protected Object[][] getContents() {
    Object[][] resources = new Object[3][2];
    resources[0][0] = "title";
    resources[0][1] = "Mein Program";
    resources[1][0] = "StopText";
    resources[1][1] = "Anschlag";
    resources[2][0] = "StartText";
    resources[2][1] = "Anfang";
    return resources;
  }
}
