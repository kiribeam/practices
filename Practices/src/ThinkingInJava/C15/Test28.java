class Generic1<T>{
  public void f(T t){
    System.out.println(t);
  }
}

class Generic2<T>{
  public T f(T t){
    return t;
  }
}

public class Test28{
  static  <T> void f1(Generic1<? super T> g1, T t){
    g1.f(t);
  }
  static <T> void f2(Generic2<T> g2, T t){
    g2.f(t);
  }
  public static void main(String[] args){
    f1(new Generic1<Integer>(), 5);
    f2(new Generic2<Integer>(), 5);
  }
}
