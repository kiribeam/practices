import java.util.*;

public class Generators{
  public static <T, S extends Collection<T>> S
    fill(S coll, Generator<T> gen, int n){
      for(int i=0; i<n; i++)
        coll.add(gen.next());
      return coll;
    }

  public static void main(String[] args){
    List<Coffee> coffee = fill(
        new ArrayList<Coffee>(), new CoffeeGenerator(), 4
        );
    for(Coffee c : coffee)
      System.out.println(c);
    ArrayList<Integer> fnumbers = fill(
        new ArrayList<Integer>(), new Fibonacci(), 12
        );
    for(int i : fnumbers)
      System.out.println(i + " , ");
  }
}
