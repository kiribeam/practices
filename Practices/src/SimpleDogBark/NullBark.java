/* That we could not hear it easily*/
public class NullBark implements BarkAction{
  public void bark(){
    this.action();
  }
  public void action(){
    System.out.println("........(Silence)");
  }
}
