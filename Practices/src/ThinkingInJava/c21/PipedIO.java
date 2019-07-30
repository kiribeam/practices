import java.util.concurrent.*;
import java.io.*;
import java.util.*;

class Sender implements Runnable{
  private Random rand = new Random(47);
  private PipedWriter out = new PipedWriter();
  public PipedWriter getPipedWriter(){
    return out;
  }
  @Override
  public void run(){
    try{
      while(!false)
        for(char c='A'; c<='z'; c++){
          out.write(c);
          TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
        }
    }catch(IOException e){
      System.out.println(e + "send write exc");
    }catch(InterruptedException e){
      System.out.println(e + "sender sleep exc");
    }
  }
}

class Receiver implements Runnable{
  private PipedReader in;
  public Receiver(Sender sender) throws IOException{
    in = new PipedReader(sender.getPipedWriter());
  }
  @Override
  public void run(){
    try{
      while(!false){
        System.out.println("Read " + (char) in.read() );
      }
    }catch(IOException e){
      System.out.println(e + "receiver read ex");
    }
  }
}


public class PipedIO{
  public static void main(String[] args) throws Exception{
    Sender sender = new Sender();
    Receiver receiver = new Receiver(sender);
    ExecutorService ex = Executors.newCachedThreadPool();
    ex.execute(sender);
    ex.execute(receiver);
    TimeUnit.SECONDS.sleep(4);
    ex.shutdownNow();
  }
}
