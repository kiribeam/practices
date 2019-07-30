
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class Car{
  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();
  private boolean waxOn = false;
  public void waxed(){
    lock.lock();
    try{
      waxOn = true;
      condition.signalAll();
    } finally {
      lock.unlock();
    }
  }

  public void buffed(){
    lock.lock();
    try{
      waxOn = false;
      condition.signalAll();
    }finally{
      lock.unlock();
    }
  }

  public void waitForWaxing() throws InterruptedException{
    lock.lock();
    try{
      while(waxOn == false)
        condition.await();
    }finally{
      lock.unlock();
    }
  }

  public void waitForBuffing() throws InterruptedException{
    lock.lock();
    try{
      while(waxOn == true)
        condition.await();
    }finally{
      lock.unlock();
    }
  }
}

class WaxOn implements Runnable{
  private Car car;
  public WaxOn(Car c){
    car = c;
  }

  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        System.out.println("Wax on!");
        TimeUnit.MILLISECONDS.sleep(200);
        car.waxed();
        car.waitForBuffing();
      }
    }catch(InterruptedException e){
      System.out.println("Exiting via interrupt");
    }
    System.out.println("Ending wax on task");
  }
}

class WaxOff implements Runnable{
  private Car car;
  public WaxOff(Car c){
    car = c;
  }

  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        car.waitForWaxing();
        System.out.println("Wax off!");
        TimeUnit.MILLISECONDS.sleep(200);
        car.buffed();
      }
    }catch(InterruptedException e){
      System.out.println("Exiting via interrupted");
    }
    System.out.println("Ending wax off");
  }
}

public class WaxOMatic2{
  public static void main(String[] args)throws Exception{
    Car car = new Car();
    ExecutorService ex = Executors.newCachedThreadPool();
    ex.execute(new WaxOff(car));
    ex.execute(new WaxOn(car));
    TimeUnit.SECONDS.sleep(5);
    ex.shutdownNow();
  }
}
