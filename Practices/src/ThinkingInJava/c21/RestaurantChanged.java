import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class Mealc{
  private final int orderNum;
  public Mealc(int orderNum){
    this.orderNum = orderNum;
  }
  @Override
  public String toString(){
    return "Meal " + orderNum;
  }
}

class WaitPersonc implements Runnable{
  private RestaurantChanged rest;
  public WaitPersonc(RestaurantChanged r){
    rest = r;
  }
  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        try{
          rest.lock.lock();
          while(rest.meal == null)
            rest.condition.await();
          System.out.println("wait got " + rest.meal);
          rest.meal = null;
          rest.condition.signalAll();
        }finally{
          rest.lock.unlock();
        }
      }
    }catch(InterruptedException e){
      System.out.println("exiting via inter wait");
    }
  }
}

class Chefc implements Runnable{
  private RestaurantChanged rest;
  private int count = 0;
  public Chefc(RestaurantChanged r){
    rest = r;
  }
  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        try{
          rest.lock.lock();
          while(rest.meal != null)
            rest.condition.await();
          if(++count == 10){
            System.out.println("out of food, closing");
            rest.exec.shutdownNow();
          }
          System.out.println("Order up");
          rest.meal = new Mealc(count);
          rest.condition.signalAll();
        }finally{
          rest.lock.unlock();
        }
        TimeUnit.MILLISECONDS.sleep(100);
      }
    }catch(InterruptedException e){
      System.out.println("Chef exit via inter");
    }
  }
}

public class RestaurantChanged{
  Mealc meal;
  ExecutorService exec = Executors.newCachedThreadPool();
  WaitPersonc wp = new WaitPersonc(this);
  Chefc cc = new Chefc(this);
  Lock lock = new ReentrantLock();
  Condition condition = lock.newCondition();
  public RestaurantChanged(){
    exec.execute(wp);
    exec.execute(cc);
  }

  public static void main(String[] args) throws Exception{
    new RestaurantChanged();
  }
}
