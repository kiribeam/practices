package desigpattern.command;
public class ToyDog extends Toy implements Dog{
  public void bark(){
    System.out.println("Dong Dong");
  }
  public void run(){
    System.out.println("DADADADA");
  }
  public void action1(){
    bark();
  }
  public void action2(){
    run();
  }
}
