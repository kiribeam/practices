import java.util.*;

public class TestListIter{
  public static void main(String[] a){
    ArrayList<Integer> al = new ArrayList<>();
    for(int i=0; i<10; i++)
      al.add(i + 10);
    ListIterator<Integer> iter = al.listIterator();
    int j = iter.next();
    System.out.println("Now j is :" + j);
    j = iter.next();
    System.out.println("NOw j is: " + j);
    System.out.println("Now next index is : " + iter.nextIndex());
    System.out.println("Now  pre is" + iter.previous());
    System.out.println("NOw  next is: " + iter.next());
    iter.add(77);
    System.out.println("Now iter next is " + iter.next());
    System.out.println("Now pre is" + iter.previous());
    System.out.println(" No pre is" + iter.previous());
    iter.remove();
    System.out.println("NOw next is " + iter.next());
    iter.set(88);
    System.out.println("Now next is" + iter.next());
    System.out.println("NOw pre is" + iter.previous());
    System.out.println("Now pre si" + iter.previous());
  }
}
