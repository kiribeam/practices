/*This source file defines running action that normal dogs,cats or
 * some animals with legs moves*/
public class NormalRun implements RunAction{
  public void run(){
    this.action();
  }
  public void action(){
    System.out.println("This action is running by legs!");
    System.out.println("I'm running by legs!");
  }
}
