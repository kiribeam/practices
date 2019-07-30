import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class AttemptLocking{
  private ReentrantLock lock = new ReentrantLock();
  private ReentrantLock lock2 = new ReentrantLock();
  public void untimed(){
    boolean captured = lock.tryLock();
    lock.tryLock();
    try{
      System.out.println("trylock() : " + captured);
    }finally{
      if(captured)
        lock.unlock();
    }
  }
  public void untimed2(){
    boolean captured = lock2.tryLock();
    try{
      System.out.println("TryLock2(): " + captured);
    }finally{
      if(captured)
        lock2.unlock();
    }
  }
  public void timed(){
    boolean captured = false;
    try{
      captured = lock.tryLock(2, TimeUnit.SECONDS);
    }catch(InterruptedException e){
      throw new RuntimeException(e);
    }
    try{
      System.out.println("try lock(2, us): " + captured);
    }finally{
      if(captured )
        lock.unlock();
    }
  }

  public static void main(String[] arsgs)throws Exception{
    final AttemptLocking al = new AttemptLocking();
    al.untimed();
    al.timed();
    new Thread(){
      {setDaemon(true);}
      @Override
      public void run(){
        al.lock2.lock();
        al.lock.lock();
        System.out.println("acquired");
        while(true) ;
      }
    }.start();
    TimeUnit.SECONDS.sleep(1);
    al.untimed();
    al.timed();
    al.untimed2();
    al.untimed2();
  }
}
