public class ResponsiveUi extends Thread{
  private static volatile double d = 1;
  public ResponsiveUi(){
    setDaemon(true);
    start();
  }
  public void run(){
    while(true)
      d++;
  }
  public static void main(String[] args) throws Exception{
    new ResponsiveUi();
    System.in.read();
    System.out.println(d);
  }

}
