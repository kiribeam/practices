/* This source file defined a simple series of Animal's properties and actions .
 * 2017.06.07 Wednesday by Kiri*/
public abstract class Animal {
  String name;
  Action run;
  Action bark;
  public void run(){
    run.action();
  }
  public void bark(){
    bark.action();
  }
  public Animal(String s) {
    this.name = s;
  }
  public void printName() {
    System.out.println(name);
  }
}
