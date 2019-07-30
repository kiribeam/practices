public class BasicThreads{
  public static void main(String[] args)throws Exception{
    Thread t = new Thread(new LiftOff());
    t.start();
    Thread.sleep(1);
    System.out.println("Waiting for LiftOff");
  }
}
