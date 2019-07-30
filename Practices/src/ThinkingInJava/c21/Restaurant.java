import java.util.concurrent.*;

class Meal{
  private final int orderNum;
  public Meal(int num){
    orderNum = num;
  }
  @Override
  public String toString(){
    return "Meal " + orderNum;
  }
}

class WaitPerson implements Runnable{
  private Restaurant restaurant;
  public WaitPerson(Restaurant r){
    restaurant = r;
  }

  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        synchronized(this){
          while(restaurant.meal == null)
            wait();
        }
        System.out.println("WaitPerson got " + restaurant.meal);
        synchronized(restaurant.chef){
          restaurant.meal = null;
          restaurant.chef.notifyAll();
        }
      }
    }catch(InterruptedException e){
      System.out.println("Interrupted waitperson");
    }
  }
}

class Chef implements Runnable{
  private Restaurant restaurant;
  private int count = 0;
  public Chef(Restaurant r){
    restaurant = r;
  }

  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        synchronized(this){
          while(restaurant.meal != null)
            wait();
        }
        if(++count == 10){
          System.out.println("Out of food, closing");
          restaurant.ex.shutdownNow();
          return;
        }
        System.out.println("Order up!");
        synchronized(restaurant.waitPerson){
          restaurant.meal = new Meal(count);
          restaurant.waitPerson.notifyAll();
        }
        TimeUnit.MILLISECONDS.sleep(100);
      }
    }catch(InterruptedException e){
      System.out.println("Chef interrupted");
    }
  }
}


public class Restaurant{
  Meal meal;
  ExecutorService ex = Executors.newCachedThreadPool();
  WaitPerson waitPerson = new WaitPerson(this);
  Chef chef = new Chef(this);

  public Restaurant(){
    ex.execute(waitPerson);
    ex.execute(chef);
  }

  public static void main(String[] artgs){
    new Restaurant();
  }
}

