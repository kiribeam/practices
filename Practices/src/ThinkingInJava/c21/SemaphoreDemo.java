import java.util.*;
import java.util.concurrent.*;

class CheckoutTask<T> implements Runnable{
  private static int counter = 0;
  private final int id = counter++;
  private Pool<T> pool;
  public CheckoutTask(Pool<T> pool){
    this.pool = pool;
  }

  @Override
  public void run(){
    try{
      T item = pool.checkOut();
      System.out.println(this + "checkedout" + item);
      TimeUnit.SECONDS.sleep(1);
      System.out.println(this + "checking in " + item);
      pool.checkIn(item);
    }catch(InterruptedException e){
      ;
    }
  }
  @Override
  public String toString(){
    return "CheckoutTask " + id;
  }
}

public class SemaphoreDemo{
  final static int SIZE = 25;
  public static void main(String[] args) throws Exception{
    final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
    ExecutorService ex = Executors.newCachedThreadPool();
    for(int i=0; i<SIZE; i++){
      ex.execute(new CheckoutTask<Fat>(pool));
    }
    System.out.println("All checkout task created");
    List<Fat> list = new ArrayList<>();
    for(int i=0; i<SIZE; i++){
      Fat f = pool.checkOut();
      System.out.print(i + " main() thread checked out ");
      f.operation();
      list.add(f);
    }
    Future<?> blocked = ex.submit(new Runnable(){
      @Override
      public void run(){
        try{
          pool.checkOut();
        }catch(InterruptedException e){
          System.out.println("checked out interrupted");
        }
      }
    });
    TimeUnit.SECONDS.sleep(2);
    blocked.cancel(true);
    System.out.println("checking in objects in " + list);
    for(Fat f : list)
      pool.checkIn(f);
    for(Fat f: list)
      pool.checkIn(f);
    ex.shutdown();
  }
}
