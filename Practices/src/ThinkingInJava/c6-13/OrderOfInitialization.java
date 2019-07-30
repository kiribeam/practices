class House {
  Window w1 = new Window(1);
  House(){
    System.out.println("House()");
    w3 = new Window(33);
  }
  Window w2 = new Window(2);
  Window w3 = new Window(3);
  void f() {System.out.println("f()");}
}

class Window{
  Window(int i){ System.out.println("Window(" + i +" )"); }
}
class OrderOfInitialization{
  public static void main(String[] args){
    House h = new House();
    h.f();
  }
}
