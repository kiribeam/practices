interface ClassInIn{
  void howdy();
  class Test implements ClassInIn{
    public void howdy(){
      System.out.println("Howdy!");
    }
    public static void main(String [] args){
      new Test().howdy();
    }
  }
}
