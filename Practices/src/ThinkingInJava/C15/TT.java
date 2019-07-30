import java.util.*;

class SS{
  public <T, S extends Collection<T>> S Sonomama( S a, T b){
    a.add(b);
    return a;
  }
}
public class TT{
  public static void main(String[] args){
    SS as = new SS();
    ArrayList<Integer> il = new ArrayList<>();
    System.out.println(as.Sonomama(il, 5).getClass());
  }
}
