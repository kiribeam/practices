class A {
  public A(){
    System.out.println("A created!");
  }
}

class B {
  public B(){
    System.out.println("B created!");
  }
}

class C extends A{
  public B b = new B();
}

public class ExtendsSequence{
  public static void main(String[] a){
    C c = new C();
  }
}
