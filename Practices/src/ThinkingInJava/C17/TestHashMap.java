import java.util.*;

public class TestHashMap{
  public static void main(String[] a){
    Map<String, Integer> map = new HashMap<>();
    map.put("abc", 1);
    map.put("dd", 2);
    String s = new String("abc");
    map.put(s, 3);
    String b = "ab";
    s = b + "c";
    map.put(s, 4);
    System.out.println(map);
  }
}
