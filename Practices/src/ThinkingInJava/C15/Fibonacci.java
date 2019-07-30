public class Fibonacci implements Generator<Integer>{
  private int count = 0;
  public Integer next(){
    return fib(count ++);
  }

  private int fib(int n){
    return fib_iter(0, 1, n);
  }
  private int fib_iter(int a , int b, int count){
    if(count == 0) return b;
    else return fib_iter(b, a+b, count-1);
  }

  public static void main(String[] args){
    Fibonacci gen = new Fibonacci();
    for(int i=0; i<18; i++)
      System.out.println(gen.next() + " ");
  }
}
