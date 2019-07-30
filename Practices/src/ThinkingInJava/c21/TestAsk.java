import java.util.*;
public class TestAsk{
  public static void main(String[] args){
    List<?> list1 = new ArrayList<>();
    List<Object> list2 = new ArrayList<>();
    List list3 = new ArrayList();
    list1.add(new TestAsk.Fruit());
    list1.add(new TestAsk.Apple());
    list1.add(new TestAsk.Tomato());
    list2.add(new TestAsk.Fruit());
    list3.add(new TestAsk.Fruit());
  }
  static class Fruit{;}
  static class Apple extends Fruit{;}
  static class Tomato{;}
}
