package desigpattern.factory;
public class ZakuFactory extends MSFactory{
  public Zaku create(){
    return new Zaku();
  }
}
