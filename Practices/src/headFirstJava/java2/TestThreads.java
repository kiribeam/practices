public class TestThreads{
  public static void main(String[] args) {
    ThreadOne t1 = new ThreadOne();
    Thread one = new Thread(t1);
    ThreadTwo t2 = new ThreadTwo();
    Thread two = new Thread(t2);
    one.start();
    two.start();
  }

}
class Accum {
  private static Accum a = new Accum();
  private int counter = 0;
  private Accum() {
  }
  public void updateCounter(int add) {
    counter += add;
  }
  public static Accum getAccum() {
    return a;
  }
  public int getCount() {
    return counter;
  }
}

class ThreadOne implements Runnable {
    Accum a = Accum.getAccum();
  public void run() {
    for(int x=0; x<98; x++) {
      a.updateCounter(1000);
      try {
        Thread.sleep(50);
      } catch(InterruptedException ex) {
        ex.printStackTrace();
      }
    }
    System.out.println("one "+ a.getCount());
  }
}

class ThreadTwo implements Runnable {
  Accum a = Accum.getAccum();
  public void run() {
   for(int x=0; x<99; x++) {
      a.updateCounter(1);
      try {
        Thread.sleep(50);
      } catch(InterruptedException ex) {
        ex.printStackTrace();
      }
    }
    System.out.println("two "+ a.getCount());
  }
}
