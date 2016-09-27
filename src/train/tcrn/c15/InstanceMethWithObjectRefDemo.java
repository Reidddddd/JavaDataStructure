package train.tcrn.c15;

public class InstanceMethWithObjectRefDemo {
  static <T> int counter(T[] vals, MyFunc2<T> f, T v) {
    int count = 0;
    for (T val : vals) {
      if (f.func(val, v)) count++;
    }
    return count;
  }

  public static void main(String[] args) {
    int count = 0;

    HighTemp[] weekDayHighs =
        { new HighTemp(89), new HighTemp(82), new HighTemp(90), new HighTemp(89),
        new HighTemp(89), new HighTemp(91), new HighTemp(84), new HighTemp(83) };
    count = counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(89));
    System.out.println(count + " days had a high of 89");
    count = counter(weekDayHighs, HighTemp::lessThanTemp, new HighTemp(89));
    System.out.println(count + " days had a high less than 89");

    HighTemp[] weekDayHighs2 =
        { new HighTemp(32), new HighTemp(12), new HighTemp(24), new HighTemp(19),
        new HighTemp(18), new HighTemp(12), new HighTemp(-1), new HighTemp(13) };
    count = counter(weekDayHighs2, HighTemp::sameTemp, new HighTemp(12));
    System.out.println(count + " days had a high of 12");
    count = counter(weekDayHighs2, HighTemp::lessThanTemp, new HighTemp(19));
    System.out.println(count + " days had a high less than 19");
  }
}

interface MyFunc2<T> {
  boolean func(T v1, T v2);
}

class HighTemp {
  private int hTemp;

  public HighTemp(int ht) {
    hTemp = ht;
  }

  boolean sameTemp(HighTemp h2) {
    return hTemp == h2.hTemp;
  }

  boolean lessThanTemp(HighTemp h2) {
    return hTemp < h2.hTemp;
  }
}