package desigpattern.factory;
public class EF extends Country{
  private static EF ef = new EF();
  private EF(){
    super("EF",new GmFactory());
  }
  public void fight(){
    MS ms = createMS();
    System.out.println(ms.getType() + " hassin!");
  }
  public static EF getInstance(){
    return ef;
  }
}
