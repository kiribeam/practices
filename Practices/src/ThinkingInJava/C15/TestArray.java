public class TestArray{
  public static void main(String[] args){
    int[] a = new int[5];
    int[] b = new int[8];
    int[] c = a;
    System.out.println(c.length);
    c = b;
    System.out.println(c.length);
  }
}
