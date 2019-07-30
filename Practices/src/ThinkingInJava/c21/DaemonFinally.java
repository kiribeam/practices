import java.util.concurrent.*;

class ADaemon implements Runnable{
  public void run(){
    try{
      System.out.println("start daemon");
      TimeUnit.SECONDS.sleep(1);
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      System.out.println("ok");
    }
  }
}

public class DaemonFinally{
  public static void main(String[] args) throws Exception{
    Thread t = new Thread(new ADaemon());
    t.setDaemon(true);
    t.start();
  }
}
