package dogbark;
//this source file defines a new kind of dog
public class BlackDog extends Dog{
  //a dog must be named 
  public BlackDog(String name){
    super(name);
  }
  // define a method bark
  public void bark() {
    System.out.println("Ruff! Ruff!");
  }
}
