/* This is just a test drive*/
import java.util.*;
public class TestAnimals{
  public static void main(String[] args) {
    ArrayList<Animal> animals = new ArrayList<>();
    animals.add(new BlackDog("Kuro"));//create new black dog
    animals.add(new BlackCat("Nyako"));// create new cat named Nyako
    for(Animal a: animals) {
      a.printName();
      a.run();
      a.bark();
    }//print name and action one by one
  }
}
