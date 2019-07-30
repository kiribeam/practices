public class TestInt{
  public static void main(String[] args){
    Integer i1 = 0;
    Integer i2 = 0;
    for(int i=0; i<300; i++){
      System.out.println("for case " + i + ":");
      if(i1 == i2) System.out.println("i1 == i2");
      else System.out.println("i1 != i2");
      i1 += i;
      i2 += i;
    }
  }
}
