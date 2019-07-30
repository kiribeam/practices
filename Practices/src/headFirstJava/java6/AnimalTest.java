import java.util.*;
public class AnimalTest{
  public static void main(String[] args) {
    ArrayList<Dog> dogs = new ArrayList<Dog>();
    List<Dog> doglist = dogs;
    ArrayList<Object> objects = new ArrayList<Object>();
    List<Object> object = objects;
    ArrayList<Animal> animals = dogs;
  }
}

class Dog extends Animal {
  ;
}
