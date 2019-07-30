import java.util.concurrent.*;

class NeedsCleanup{
  private final int id;
  public NeedsCleanup(int ident){
    id = ident;
    System.out.println("Needs cleanup " + id);
  }
  public void cleanup(){
    System.out.println("Cleaning up " + id);
  }
}

class Blocked3 implements Runnable{
  private volatile double d = 0.0;
  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        NeedsCleanup n1 = new NeedsCleanup(1);
        try{
          System.out.println("SleepIng");
          TimeUnit.SECONDS.sleep(1);
          NeedsCleanup n2 = new NeedsCleanup(2);
          try{
            System.out.println("Calculating " );
            for(int i=1; i<2500000000l; i++)
              d = d+ (Math.PI + Math.E) / d;
            System.out.println("finished operation");
          }finally{
            n2.cleanup();
          }
        }finally{
          n1.cleanup();
        }
      }
      System.out.println("exiting via while() test");
    }catch(InterruptedException e){
      System.out.println("exiting via i-excep");
    }
  }
}


public class InterruptingIdiom{
  public static void main(String[] args) throws Exception{
    if(args.length != 1){
      System.out.println("usage delay in ms");
      System.exit(1);
    }
    Thread t = new Thread(new Blocked3());
    t.start();
    TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
    t.interrupt();
  }
}
