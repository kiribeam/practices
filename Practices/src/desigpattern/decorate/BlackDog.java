package desigpattern.decorate;
public class BlackDog extends Dog{
  public BlackDog(String n){
    name = n;
    comment = "A black dog";
  }
  public void bark(){
    System.out.println("Ruff,Ruff!");
  }
}
