import java.util.*;
public class Testarraylistas{
  public static void main(String[] args){
    String[] strings = {"aa", "bb", "cc"};
    System.out.println(Arrays.toString(strings));
    List<String> ls = Arrays.asList(strings);
    System.out.println(ls);
    ls.set(0, "dd");
    System.out.println(ls);
    System.out.println(Arrays.toString(strings));
  }
}
