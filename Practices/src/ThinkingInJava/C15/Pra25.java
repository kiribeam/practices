interface Inter1{ 
  void f();
}
interface Inter2{
  void g();
}

public class Pra25{
  public <P extends Inter1> void f1(P p ){
    p.f();
  }
  public <G extends Inter2> void f2(G g){
    g.g();
  }
  class I1 implements Inter1{
    public void f(){
      System.out.println("f runs");
    }
  }
  class I2 implements Inter2{
    public void g(){
      System.out.println("g runs");
    }
  }

  public static void main(String [] args){
    Pra25 p25 = new Pra25();
    p25.f1(p25.new I1());
    p25.f2(p25.new I2());
  }
}


