/* This action defines a cat's sweet smile*/
public class CatBark implements BarkAction{
  public void bark(){
    this.action();
  }
  public void action(){
    System.out.println("A cat says");
    System.out.println("Nya~ Nya~");
  }
}
