import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class Productor implements Runnable{
  private ProductorAndConsumer pc;
  public Productor(ProductorAndConsumer pc){
    this.pc = pc;
  }
  public void produce(){
    try{
      while(!Thread.interrupted()){
        synchronized(pc){
          while(pc.list.size() == 5)
            pc.wait();
        }
        System.out.println("before pro list size is" + pc.list.size());
        Thread.sleep(3);
        synchronized(pc){
          System.out.println("Start produce " + ++pc.count);
          pc.list.add(pc.count);
          System.out.println("after pro now list is" + pc.list.size());
          pc.notifyAll();
        }
      }
    }catch(InterruptedException e){
      System.out.println("interrupted");
      return;
    }
  }
  @Override
  public void run(){
    produce();
  }
}
class Consumer implements Runnable{
  private ProductorAndConsumer pc;
  public Consumer(ProductorAndConsumer pc){
    this.pc = pc;
  }
  public void consume(){
    try{
      while(!Thread.interrupted()){
        synchronized(pc){
          while(pc.list.size() <= 0)
            pc.wait();
        }
        System.out.println("before consum list size is" + pc.list.size());
        Thread.sleep(5);
        synchronized(pc){
        System.out.println("Start consume");
          int i = pc.list.remove(0);
          System.out.println("consume " + i);
          System.out.println("after consume now list is" + pc.list.size());
          pc.notifyAll();
        }
      }
    }catch(InterruptedException e){
      System.out.println("interrupted");
      return;
    }
  }
  @Override
  public void run(){
    consume();
  }
}


public class ProductorAndConsumer{
  public int count = 0;
  public final int SIZE = 5;
  public LinkedList<Integer> list = new LinkedList<>();
  public static void main(String[] args)throws Exception{
    ProductorAndConsumer pc = new ProductorAndConsumer();
    ExecutorService ex = Executors.newCachedThreadPool();
    ex.execute(new Productor(pc));
    ex.execute(new Consumer(pc));
    Thread.sleep(200);
    ex.shutdownNow();
  }
}
