public class OuterClass{
  private int i = 0;
  public void showi(){
    System.out.println(i);
  }

  class Inner{
    public void change(int j){
      i = j;
    }
  }
  
  public static Inner sCreate(){
    return new Inner();
  }
  public Inner create(){
    return new Inner();
  }
  public static void main(String[] args){
    OuterClass out = new OuterClass();
    out.showi();
    OuterClass.Inner in1 = new OuterClass().new Inner();
    in1.change(5);
    out.showi();
    OuterClass.Inner in2 = OuterClass.sCreate();
    in2.change(6);
    out.showi();
    OuterClass.Inner in3 = out.create();
    in3.change(7);
    out.showi();


  }
}
