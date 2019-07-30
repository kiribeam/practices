
public class HiddenC{
  public static A makeA(){
    return new C();
  }
}
class C implements A{
  public void f(){
    System.out.println("public c.f");
  }
  public void g(){
    System.out.println("public c.g");
  }
  void u(){
    System.out.println(" c pack u");
  }

  protected void v(){
    System.out.println(" c protected v");
  }
  private void w(){
    System.out.println("c.private w");
  }
}

