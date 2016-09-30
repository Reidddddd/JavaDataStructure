package train.tcrn.c30;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionDemo2 {
  public static void main(String[] args) {
    A a = new A();
    Class<?> c = a.getClass();
    System.out.println("Public Methods:");
    for (Method method : c.getDeclaredMethods()) {
      int modifiers = method.getModifiers();
      if (Modifier.isPublic(modifiers)) System.out.println("Public: " + method.getName());
      if (Modifier.isPrivate(modifiers)) System.out.println("Private: " + method.getName());
      if (Modifier.isProtected(modifiers)) System.out.println("Protected: " + method.getName());
    }
  }
}

class A {
  public void a1() {}
  public void a2() {}
  protected void a3() {}
  private void a4() {}
}
