public class City{
  static int count = 0;
  final int id;
  final double x;
  final double y;
  public City(double x, double y){
    this.x = x;
    this.y = y;
    synchronized (this){
      this.id = count;
      count++;
    }
  }

}
