import java.util.*;

public class CopyingArrays{
  public static void main(String[] a){
    int[] i = new int[7];
    int[] j = new int[15];
    Arrays.fill(i, 47);
    Arrays.fill(j, 99);
    System.arraycopy(i,0,j,0,i.length);
    System.out.println(" j: " + Arrays.toString(j));
    System.out.println("i: " + Arrays.toString(i));
    i[5] = 666;
    System.out.println(" j: " + Arrays.toString(j));
    System.out.println("i: " + Arrays.toString(i));
  }
}
