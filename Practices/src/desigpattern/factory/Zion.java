package desigpattern.factory;
public class Zion extends Country{
  private static Zion zion = new Zion();
  private Zion(){
    super("Zion", new ZakuFactory());
  }
  public void fight(){
    MS ms = createMS();
    System.out.println(ms.getType() + "  hassin! Zick Zion!");
  }
  public static Zion getInstance(){
    return zion;
  }
}
