import java.lang.reflect.*;

class MethodSelector implements InvocationHandler{
  private Object proxied;
  public MethodSelector(Object proxied){
    this.proxied = proxied;
  }
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
    if(method.getName().equals("interesting"))
      System.out.println("Proxy detected the interesting method");
    return method.invoke(proxied, args);
  }
}

interface SomeMethods{
  void boring1();
  void boring2();
  void interesting(String arg);
  void boring3();
}

class Implementation implements SomeMethods{
  public void boring1(){
    System.out.println("Boring1");
  }
  public void boring2(){
    System.out.println("Boring2");

  }

  public void interesting(String arg){
    System.out.println("Interesting :" + arg);
  }
  public void boring3(){
    System.out.println("Boring3");
  }
}

public class SelectingMethods{
  public static void main(String[] args){
    SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(
        SomeMethods.class.getClassLoader(),
        new Class[] {SomeMethods.class},
        new MethodSelector(new Implementation())
        );
    proxy.boring1();
    proxy.boring3();
    proxy.interesting("bonono");
    proxy.boring2();
  }
}

