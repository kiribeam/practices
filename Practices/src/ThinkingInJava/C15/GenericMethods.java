public class GenericMethods{
  public <T, C, G> void f(T x, C y, G z){
    System.out.println(x.getClass().getName() + "  " + y.getClass().getName() + "\n " + z.getClass().getName());
  }
  public static void main(String[] args){
    GenericMethods gm = new GenericMethods();
    gm.f("", gm, 666);
  }
}
