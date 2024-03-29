import java.util.*;

public class IterableFibonacci extends Fibonacci implements Iterable<Integer>{
  private int n;
  public IterableFibonacci(int count) {
    n = count;
  }

  public Iterator<Integer> iterator(){
    return new Iterator<Integer>(){
      public boolean hasNext(){ return n>0; };
      public Integer next(){
        n--;
        return IterableFibonacci.this.next();
      }
      public void remove(){
        throw new RuntimeException();
      }
    };
  }
  public static void main(String[] args){
    for(int i: new IterableFibonacci(18))
      System.out.println(i + " ");
  }
}
