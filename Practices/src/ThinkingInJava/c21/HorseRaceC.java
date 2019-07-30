import java.util.concurrent.*;
import java.util.*;

class Horsec implements Runnable{
  private static int counter = 0;
  private final int id = counter ++;
  private int strides = 0;
  private static CyclicBarrier barrier;
  private static Random rand = new Random(47);
  public Horsec(CyclicBarrier cy){
    barrier = cy;
  }


  public synchronized int getStrides(){
    return strides;
  }
  @Override
  public void run(){
    try{
      while(!Thread.interrupted()){
        synchronized(this){
          strides += rand.nextInt(3);
        }
        System.out.println("Waiting");
        barrier.await();
      }
    }catch(InterruptedException e){
      ;
    }catch(BrokenBarrierException e){
      throw new RuntimeException(e);
    }
  }
  @Override
  public String toString(){
    return "Horse " + id + "  ";
  }
  public String tracks(){
    StringBuilder s = new StringBuilder();
    for(int i=0; i<getStrides(); i++)
      s.append("*");
    s.append(id);
    return s.toString();
  }
}

public class HorseRaceC{
  static final int FinishLine = 75;
  private List<Horsec> horses = new ArrayList<>();
  private ExecutorService ex = Executors.newCachedThreadPool();
  private CyclicBarrier barrier;
  public HorseRaceC(int nHorses, final int pause){
    barrier = new CyclicBarrier(nHorses, new Runnable(){
      @Override
      public void run(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<FinishLine; i++)
          sb.append("=");
        System.out.println(sb);
        for(Horsec horse : horses)
          System.out.println(horse.tracks());
        for(Horsec horse: horses)
          if(horse.getStrides() >= FinishLine){
            System.out.println(horse + "won!");
            ex.shutdownNow();
            return;
          }
        try{
          System.out.println("Sleeping 1");
          TimeUnit.MILLISECONDS.sleep(pause);
          System.out.println("sleeping 2");
          TimeUnit.MILLISECONDS.sleep(pause);
        }catch(InterruptedException e){
          System.out.println("barrier-action sleep interrupted");
        }
      }
    });
    for(int i=0; i<nHorses; i++){
      Horsec horse = new Horsec(barrier);
      horses.add(horse);
      ex.execute(horse);
    }
  }

  public static void main(String[] args) {
    int nHorses = 7;
    int pause = 50;
    if(args.length > 0){
      int n = new Integer(args[0]);
      nHorses = n>0 ? n : nHorses;
    }
    if(args.length > 1){
      int p = new Integer(args[1]);
      pause = p>-1 ? p : pause;
    }
    new HorseRaceC(nHorses, pause);
  }
}
