public class TestBoats{
  public static void main(String[] args) {
    Boat b1 = new Boat();
    Sailboat b2 = new Sailboat();
    RowBoat b3 = new RowBoat();
    b2.setLength(32);
    b1.move();
    b3.move();
    b2.move();
  }
}
