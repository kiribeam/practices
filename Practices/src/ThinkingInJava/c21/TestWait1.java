import java.util.concurrent.*;

class BlockedWait{
  public synchronized void callWait(){
    System.out.println("waiting");
    try{
      wait();
    }catch(InterruptedException e){
      System.out.println("wait iter");
    }
  }
  public synchronized void callSleep(){
    System.out.println("sleeping");
    try{
      Thread.sleep(20000);
    }catch(InterruptedException e){
      System.out.println("sleep iter");
    }
  }
}
class WaitProcess implements Runnable{
  BlockedWait bw = new BlockedWait();
  @Override
  public void run(){
    System.out.println("now waiting");
    bw.callWait();
  }
}
class WaitProcess2 implements Runnable{
  BlockedWait bw = new BlockedWait();
  @Override
  public void run(){
    while(true){
      System.out.write('.');
    }
  }
}

public class TestWait1{
  public static void main(String[] args)throws Exception{
    ExecutorService ex = Executors.newCachedThreadPool();
    ex.execute(new WaitProcess());
    //ex.execute(new WaitProcess2());
    Thread.sleep(200);
    ex.shutdownNow();
  }
}
