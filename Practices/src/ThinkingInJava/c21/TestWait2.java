import java.util.concurrent.*;

public class TestWait2{
  public static void main(String[] args) throws Exception{
    TestWait2 tw = new TestWait2();
    ExecutorService ex = Executors.newCachedThreadPool();
    ex.execute(new Wait2(tw));
    ex.execute(new Wake2(tw));
    Thread.sleep(9000);
    ex.shutdownNow();

  }
}

class Wait2 implements Runnable{
  private TestWait2 testWait;
  public Wait2(TestWait2 w){
    testWait = w;
  }
  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        synchronized(testWait){
          testWait.wait();
          System.out.println("get lock");
       }
       while(true){
         System.out.println("in waiting");
         Thread.sleep(20);
       }
     }
    }catch(InterruptedException e){
      System.out.println("exit via interrupted");
    }
  }
}

class Wake2 implements Runnable{
  private TestWait2 testWait;
  public Wake2(TestWait2 w){
    testWait = w;
  }

  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        Thread.sleep(300);
        synchronized(testWait){
          testWait.notifyAll();
          System.out.println("Wake up all");
          while(true){
            System.out.println("in waking   xxxxx");
            Thread.sleep(200);
          }
        }
      }
    }catch(InterruptedException e){
      System.out.println("exiting via interrupt wake");
    }
  }
}
