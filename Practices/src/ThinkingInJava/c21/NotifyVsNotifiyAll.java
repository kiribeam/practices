import java.util.*;
import java.util.concurrent.*;

class Blocker{
  synchronized void waitingCall(){
    try{
      while(!Thread.interrupted()){
        wait();
        System.out.println(Thread.currentThread().getName() + " ");
      }
    }catch(InterruptedException e){
      ;
    }
  }
  synchronized void prod(){
    notify();
  }
  synchronized void prodAll(){
    notifyAll();
  }
}

class Task implements Runnable{
  static Blocker blocker = new Blocker();
  @Override
  public void run(){
    blocker.waitingCall();
  }
}

class Task2 implements Runnable{
  static Blocker blocker = new Blocker();
  @Override
  public void run(){
    blocker.waitingCall();
  }
}

public class NotifyVsNotifiyAll{
  public static void main(String[] args) throws Exception{
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i=0; i<5; i++)
      exec.execute(new Task());
    exec.execute(new Task2());
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask(){
      boolean prod = true;
      @Override
      public void run(){
        if(prod){
          System.out.println("\nnotify() ");
          Task.blocker.prod();
          prod = false;
        }else{
          System.out.print("\n notifyAll () ");
          Task.blocker.prodAll();
          prod = true;
        }
      }
    }, 400, 400);
    TimeUnit.SECONDS.sleep(5);
    timer.cancel();
    System.out.println("Task2.blocker.notifyAll() ");
    Task2.blocker.prodAll();
    TimeUnit.MILLISECONDS.sleep(500);
    System.out.println("\n shutting down");
    exec.shutdownNow();
  }
}
