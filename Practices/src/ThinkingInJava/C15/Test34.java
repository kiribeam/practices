abstract class Oya<T extends Oya<T>>{
  abstract T f(T t);
  T g(T t){
    System.out.println("Oya g");
    return f(t);
  }
}

class Kosan extends Oya<Kosan>{
  Kosan f(Kosan t){
    System.out.println("Kosan f");
    return t;
  }
  Kosan g(Kosan t){
    System.out.println("Kosan g");
    return f(t);
  }
}

/*class Ko2 extends Oya<T extends Ko2>{
  T f(T t){
    System.out.println("Ko2");
    return t;
  }
}*/

public class Test34{
  public static void main(String[] args){
    Kosan k = new Kosan();
    Oya o = new Kosan();
    k.g(k);
    o.g(k);
    o.g(o);
  }
}
