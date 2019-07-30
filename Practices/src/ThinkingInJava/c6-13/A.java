class B{
  private interface D{
    public void f();
  }
  private class D1 implements D{
    public void f(){};
  }
  public class D2 implements D{
    public void f(){};
  }
  public D getD(){
    return new D1();
  }
}
public class A{
  public static void main(String[] args){
    new B().getD();
  }
}
