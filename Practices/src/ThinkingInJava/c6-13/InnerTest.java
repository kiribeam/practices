public class InnerTest{
  public class Inner{
    public void print(){
      System.out.println("Inner print");
    }
    public InnerTest getOut(){
      return InnerTest.this;
    }
  }

  public static void main(String[] args){
    Inner in = new InnerTest(). new Inner();
    in.print();
    InnerTest out = in.getOut();
    out.print();
  }
  public void print(){
    System.out.println("out print");
  }
}
