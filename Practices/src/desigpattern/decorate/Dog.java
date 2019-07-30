package desigpattern.decorate;
public abstract class Dog{
  protected String name;
  protected String comment;
  public String getName(){
    return name;
  }
  public abstract void bark();
  public void describe(){
    System.out.println(comment + " !");
  }
}
