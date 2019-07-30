import java.util.*;
public class IterTest{
  public static void main(String[] a){
    ArrayList<Integer> ints = new ArrayList<>();
    for(int i=0; i<10; i++){
      ints.add(i);
    }
    Iterator<Integer> iter = ints.iterator();
    Integer j = iter.next();
    System.out.println(j);
  }
}
