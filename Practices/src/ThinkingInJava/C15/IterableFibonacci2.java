import java.util.*;

public class IterableFibonacci2 implements Iterable<Integer>{
  private Fibonacci fib;
  private int n;

  public IterableFibonacci2(int count){
    fib = new Fibonacci();
    n = count;
  }
  public Iterator<Integer> iterator(){
    return new Iterator<Integer>(){
      public boolean hasNext(){
       return n>0;
      }
      public Integer next(){
        n--;
        return fib.next();
      }
      public void remove(){
        throw new RuntimeException();
      }
    };
  }

  public static void main(String[] args){
    for(int i: new IterableFibonacci2(18))
      System.out.print(i + " ");
  }
}
