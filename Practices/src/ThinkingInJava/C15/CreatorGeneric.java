abstract class GenericWithCreate<T>{
  final T elements;
  GenericWithCreate(){
    elements = create();
  }
  abstract T create();
}

class X{}

class Creator extends GenericWithCreate<X> {
  X create(){
    return new X();
  }
  void f(){
    System.out.println(elements.getClass().getSimpleName());
  }
}

public class CreatorGeneric{
  public static void main(String[] args){
    Creator c = new Creator();
    c.f();
  }
}
