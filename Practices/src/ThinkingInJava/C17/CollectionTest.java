import java.util.*;

public class CollectionTest{
  public static void main(String[] args){
    List<String> list = Arrays.asList(" one two three four five six".split(" "));
    Set<String> set = new HashSet<>();
    set.add("sb");
    set.add("gg");
    set.add("kff");
    set.add("dnd");
    set.add("closers");

    List<String> sublist = Arrays.asList("one two three".split(" "));
    System.out.println("list" + " " + Collections.lastIndexOfSubList(list, sublist));
    sublist = Arrays.asList("one two four".split(" "));
    System.out.println("list" + " " + Collections.lastIndexOfSubList(list, sublist));

    Set<String> subset = new HashSet<>();
    subset.add("gg");
    subset.add("dnd");
  }
}
