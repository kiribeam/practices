import java.lang.reflect.*;

class DynamicProxyHandler implements InvocationHandler{
  private Object proxied;
  private int count = 0;
  public DynamicProxyHandler(Object proxied){
    this.proxied = proxied;
  }
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
    System.out.println(" ***** proxy: " + proxy.getClass() + 
        ", method: " + method + " , args: " + args);
    if(args != null)
      for(Object arg : args)
        System.out.println(" " + arg);
    count ++;
    System.out.println("Now used " + count + " time/s");
    System.out.println(proxy.getClass());
    Class pc = proxy.getClass();
    for(Constructor cc: pc.getConstructors())
      System.out.println(cc);
    for(Method cc: pc.getMethods())
      System.out.println(cc);
    for(Field cc: pc.getFields())
      System.out.println(cc);
    return method.invoke(proxied, args);
  }
}

public class SimpleDynamicProxy{
  public static void consumer(Interface iface){
    iface.doSomething();
    iface.somethingElse("bonono");
  }
  public static void main(String[] args){
    RealObject real = new RealObject();
    consumer(real);
    Interface proxy = (Interface) Proxy.newProxyInstance(
        Interface.class.getClassLoader(),
        new Class[]{
          Interface.class
        },
        new DynamicProxyHandler(real)
        );
    consumer(proxy);
  }
}
