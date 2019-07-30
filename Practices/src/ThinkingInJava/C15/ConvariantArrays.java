class Fruit{}
class Apple extends Fruit{}
class Orange extends Fruit{}
class Fuji extends Apple{}

public class ConvariantArrays{
  public static void main(String [] args){
    Fruit[] fruit = new Apple[10];
  }
}
