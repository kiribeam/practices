/* This source file defines only dog's bark action*/
public class DogBark implements BarkAction{
  public void bark(){
    this.action();
  }
  public void action(){
    System.out.println("Dog Bark On!");
    System.out.println("Wuff! Wuff!");
  }
}
