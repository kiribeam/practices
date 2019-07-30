import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.*;

public class ReaderWriterList<T>{
  private ArrayList<T> lockedList;
  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
  public ReaderWriterList(int size, T initialValue){
    lockedList = new ArrayList<T>(Collections.nCopies(size, initialValue));
  }
  public T set(int index, T element){
    Lock wlock = lock.writeLock();
    wlock.lock();
    try{
      return lockedList.set(index, element);
    }finally{
      wlock.unlock();
    }
  }

  public T get(int index){
    Lock rlock = lock.readLock();
    rlock.lock();
    try{
      if(lock.getReadLockCount() > 1)
        System.out.println(lock.getReadLockCount());
      return lockedList.get(index);
    }finally{
      rlock.unlock();
    }
  }
  public static void main(String[] args) throws Exception{
    ReaderWriterListTest rt = new ReaderWriterListTest(30, 1);
    Thread.sleep(5000);
    System.out.println("Total read coutn is " + rt.count);

  }
}

class ReaderWriterListTest{
  public AtomicInteger count = new AtomicInteger();
  ExecutorService ex = Executors.newCachedThreadPool();
  private final static int SIZE = 100;
  private static Random rand = new Random(47);
  private ReaderWriterList<Integer> list = new ReaderWriterList<>(SIZE, 0);
  private class Writer implements Runnable{
    int count = 0;
    @Override
    public void run(){
      try{
        for(int i=0; i<20; i++){
          list.set(i, rand.nextInt());
          count++;
          TimeUnit.MILLISECONDS.sleep(100);
        }
      }catch(InterruptedException e){;}
      System.out.println("Writer finished, shutting down" + count);
      ex.shutdownNow();
    }
  }

  private class Reader implements Runnable{
    @Override
    public void run(){
      try{
        while(!Thread.interrupted()){
          for(int i=0; i<SIZE; i++){
            list.get(i);
            count.getAndIncrement();
            TimeUnit.MILLISECONDS.sleep(1);
          }
        }
      }catch(InterruptedException e){;}
    }
  }

  public ReaderWriterListTest(int readers, int writers){
    for(int i=0; i<readers; i++)
      ex.execute(new Reader());
    for(int i=0; i<writers; i++)
      ex.execute(new Writer());
  }
}

