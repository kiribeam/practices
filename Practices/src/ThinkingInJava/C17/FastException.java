import java.util.*;

public class FastException{
  public static void main(String[] args){
    ArrayList<String> al = new ArrayList<>();
    al.add("gg");
/*    Iterator<String> it = al.iterator();
    al.add("ss");
    while(it.hasNext()){
      System.out.println("Now :" + it.next());
    }
*/
    Collection<String> c = Collections.synchronizedList(al);
    System.out.println(c);
    Iterator<String> it2 = c.iterator();
    c.add("bb");
    while(it2.hasNext())
      System.out.println("Now :" + it2.next());
  }
}
