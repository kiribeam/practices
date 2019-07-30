import java.util.ArrayList;

public class Dog {
  private String name = null;
  private String says = null;
  
  public Dog(String name, String says){
    this.name = name;
    this.says = says;
  }

  public void bark(){
    System.out.println(name + " says " + says);
    return;
  }

  public static void main(String[] argvs){
    Dog spot = new Dog("spot", "Ruff!");
    Dog scruffy= new Dog("scruffy","Wurf!");
    ArrayList<Dog> dogs = new ArrayList<>();
    dogs.add(spot);
    dogs.add(scruffy);
    for(Dog d: dogs)
      d.bark();
    return;
  }
}
