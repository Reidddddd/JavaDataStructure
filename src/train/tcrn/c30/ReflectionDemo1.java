package train.tcrn.c30;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionDemo1 {
  public static void main(String[] args) {
    try {
      Class<?> c = Class.forName("java.util.HashMap");
      System.out.println("Constructors:");
      for (Constructor<?> cons : c.getConstructors()) System.out.println(" " + cons);
      System.out.println("Fields:");
      for (Field field : c.getFields()) System.out.println(" " + field);
      System.out.println("Methods:");
      for (Method method : c.getMethods()) System.out.println(" " + method);
    } catch (ClassNotFoundException e) {
      System.out.println("Exception: " + e);
    }
  }
}
