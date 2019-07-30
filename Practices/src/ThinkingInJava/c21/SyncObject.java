class DualSynch{
  private Object syncObject = new Object();
  public synchronized void f(){
    for(int i=0; i<5; i++){
      System.out.println("f");
      Thread.yield();
    }
  }
  public void g()throws Exception{
    synchronized(this){
      for(int i=0; i<5; i++){
        System.out.println("g");
      }
    }
  }
}

public class SyncObject{
  public static void main(String[] args)throws Exception{
    final DualSynch ds = new DualSynch();
    new Thread(){
      @Override
      public void run(){
        ds.f();
      }
    }.start();
    ds.g();
  }
}
