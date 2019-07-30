public class TestXIter{
  public static double iter(double x, int time){
    if(time <= 0) return Double.NaN;
    double y = x;
    while(time > 0){
      x = y;
      y = x + 1.0/x;
      time--;
    }
    return y;
  }
  public static void main(String[] args){
    System.out.println(iter(1000, 2000000));
    double d = 100000;
    while(d>0)
      d = d*d;
    System.out.println(d);
  }
}
