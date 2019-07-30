package dogbark;
//this class is to define a new class of dog
public abstract class Dog{
  // a name for dog if it's necessary
  private String name;
  //a general version to create
  public Dog(){};
  //create a new dog with "name"
  public Dog(String name){
    this.name = name;
  }
  // define a method to get name
  public String getName(){
    return name;
  }
  //define a method to bark
  public abstract void bark();
}
