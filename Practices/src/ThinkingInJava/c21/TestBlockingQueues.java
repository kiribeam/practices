import java.util.concurrent.*;
import java.io.*;

class LiftOffRunner implements Runnable{
  private BlockingQueue<LiftOff> rockets;
  public LiftOffRunner(BlockingQueue<LiftOff> queue){
    rockets = queue;
  }
  public void add(LiftOff lo){
    try{
      rockets.put(lo);
    }catch(InterruptedException e){
      System.out.println("Iterrupted during put()");
    }
  }
  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        LiftOff rocket = rockets.take();
        rocket.run();
      }
    }catch(InterruptedException e){
      System.out.println("Waking from take()");
    }
    System.out.println("Exiting lift off runner");
  }
}


public class TestBlockingQueues{
  static void getkey(){
    try{
      new BufferedReader(new InputStreamReader(System.in)).readLine();
    }catch(IOException e){
      throw new RuntimeException(e);
    }
  }

  static void getkey(String message){
    System.out.println(message);
    getkey();
  }

  static void test(String msg, BlockingQueue<LiftOff> queue){
    System.out.println(msg);
    LiftOffRunner runner = new LiftOffRunner(queue);
    Thread t = new Thread(runner);
    t.start();
    for(int i=0; i<5; i++)
      runner.add(new LiftOff(5));
    getkey("press enter " + msg);
    t.interrupt();
    System.out.println("Finished" + msg + "test");
  }

  public static void main(String[] args){
    test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());
    test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(5));
    test("synchQueue", new SynchronousQueue<LiftOff>());
  }
}
