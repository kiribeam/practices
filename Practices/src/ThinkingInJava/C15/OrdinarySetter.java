public class OrdinarySetter{
  void set(Base base){
    System.out.println("OrdinarySetter.setBase");
  }
  Base get(Base base){
    System.out.println("Base get");
    return base;
  }
  public static void main(String[] args){
    Base base = new Base();
    Derived derived = new Derived();
    DerivedSetter ds = new DerivedSetter();
    ds.set(base);
    Base b = ds.get(base);
    ds.set(derived);
    Derived d = ds.get(derived);
    d = ds.get(base);
  }
}

class DerivedSetter extends OrdinarySetter{
  void set(Derived derived){
    System.out.println("DerivedSetter");
  }
  Derived get(Derived d){
    System.out.println("derived get");
    return d;
  }
  @Override
  Derived get(Base b){
    System.out.println("Derived get base");
    return new Derived();
  }
}

class Base{}
class Derived extends Base{}
