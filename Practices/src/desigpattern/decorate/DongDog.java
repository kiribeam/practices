package desigpattern.decorate;
public class DongDog extends Dog{
  private Dog dog;
  public DongDog(Dog d){
    dog = d;
    name = dog.name;
    comment = dog.comment + " which has been dressed well";
  }
  public void bark(){
    dog.bark();
  }
}
