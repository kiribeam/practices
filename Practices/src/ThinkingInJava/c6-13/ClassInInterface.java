public interface ClassInInterface{
  void howdy();
  class Test implements ClassInInterface{
    public void howdy(){
      System.out.println("Howdy!");
    }
    public static void main(String[] a){
      new Test().howdy();
    }
  }
}
