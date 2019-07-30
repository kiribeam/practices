class Subtype extends BasicHolder<Subtype>{}

public class CRGWithBasicHolder{
  public static void main(String[] ags){
    Subtype t1 = new Subtype();
    Subtype t2 = new Subtype();
    t1.set(t2);
    Subtype t3 = t1.get();
    t1.f();
  }
}
