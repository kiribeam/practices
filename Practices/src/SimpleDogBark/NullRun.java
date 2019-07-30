/* This source file defines a null running action to display the animals that couldn't run*/
public class NullRun implements RunAction{
  public void run(){
    this.action();
  }
  public void action(){
    System.out.println("Sorry, I can't run!....");
  }
}
