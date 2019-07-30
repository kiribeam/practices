interface Interface{
  void doSomething();
  void somethingElse(String arg);
}

class RealObject implements Interface{
  public void doSomething(){
    System.out.println("do Something");
  }

  public void somethingElse(String arg){
    System.out.println("Something Else");
  }
}

class SimpleProxy implements Interface{
  private Interface proxied;
  public SimpleProxy(Interface proxied){
    this.proxied = proxied;
  }
  public void doSomething(){
    System.out.println("Simple so something");
    proxied.doSomething();
  }
  public void somethingElse(String arg){
    System.out.println("Simple do else" + arg);
    proxied.somethingElse(arg);
  }
}

public class SimpleProxyDemo{
  public static void consumer(Interface iface){
    iface.doSomething();
    iface.somethingElse("bonono");
  }
  public static void main(String[] args){
    consumer(new RealObject());
    consumer(new SimpleProxy(new RealObject()));
  }
}
