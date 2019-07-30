import java.util.*;

public class TestIntPrint{
  public static void main(String[] args){

    ArrayList<String> al = new ArrayList<>();
    al.add("One");
    al.add("two");
    al.add("three");
    Iterator<String> it = al.iterator();
    System.out.println(it);
  }
}
