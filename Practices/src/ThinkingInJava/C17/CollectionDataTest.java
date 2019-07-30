import java.util.*;

class Government implements Generator<String>{
  String[] foundation = (" strange women lying i ponds distribution sowrds is no basis for a system of govrnment").split(" ");
  private int index;
  public String next(){
    return foundation[index++];
  }
}

public class CollectionDataTest{
  public static void main(String[] a){
    Set<String> set = new LinkedHashSet<String>(
        new CollectionData<String>(new Government(), 15)
        );
    System.out.println(set);
    set.addAll(CollectionData.list(new Government(), 15));
    System.out.println(set);
  }
}
