import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class BlockedMutex{
  private Lock lock = new ReentrantLock();
  public BlockedMutex(){
    System.out.println("in constructor " + Thread.currentThread().getName());
    lock.lock();
  }

  public void f(){
    try{
      System.out.println("in f " + Thread.currentThread().getName());
      lock.lockInterruptibly();
      System.out.println("Lock acquired in f()");
    }catch(InterruptedException e){
      System.out.println("Interrupted from lock acquisition in f");
    }
  }
}

class Blocked2 implements Runnable{
  BlockedMutex blocked = new BlockedMutex();
  @Override
  public void run(){
    System.out.println("Waiting for f() in blockedMutex");
    blocked.f();
    System.out.println("Broken out of blocked call");
  }
}

public class Interruting2{
  public static void main(String[] args) throws Exception{
    System.out.println("in main" + Thread.currentThread().getName());
    Thread t = new Thread(new Blocked2());
    t.start();
    TimeUnit.SECONDS.sleep(1);
    System.out.println("Issuing t.interrupt()");
    t.interrupt();
  }
}
