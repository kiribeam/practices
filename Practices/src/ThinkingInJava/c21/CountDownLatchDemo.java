import java.util.concurrent.*;
import java.util.*;

class TaskPortion implements Runnable{
  private static int counter = 0;
  private final int id = counter ++;
  private static Random rand = new Random(47);
  private final CountDownLatch latch;
  TaskPortion(CountDownLatch latch){
    this.latch = latch;
  }
  @Override
  public void run(){
    try{
      doWork();
      latch.countDown();
    }catch(InterruptedException e){
      ;
    }
  }

  public void doWork() throws InterruptedException{
    TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
    System.out.println(this + "complete");
  }
  @Override
  public String toString(){
    return String.format("%1$-3d ", id);
  }
}


class  WaitingTask implements Runnable{
  private static int counter = 0;
  private final int id = counter++;
  private final CountDownLatch latch;
  WaitingTask(CountDownLatch latch){
    this.latch = latch;
  }
  @Override
  public void run(){
    try{
      latch.await();
      System.out.println("Latch barrier passed for " + this);
    }catch(InterruptedException e){
      System.out.println(this + " interrupted");
    }
  }
  @Override
  public String toString(){
    return String.format("Waiting for tast %1$-3d", id);
  }
}

public class CountDownLatchDemo{
  static final int SIZE = 100;
  public static void main(String[] args) throws Exception{
    ExecutorService exec = Executors.newCachedThreadPool();
    CountDownLatch latch = new CountDownLatch(SIZE);
    for(int i=0; i<10; i++)
      exec.execute(new WaitingTask(latch));
    for(int i=0; i<SIZE; i++)
      exec.execute(new TaskPortion(latch));
    System.out.println("launched all tasks");
    exec.shutdown();
  }
}
