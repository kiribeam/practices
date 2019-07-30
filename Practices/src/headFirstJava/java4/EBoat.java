public class Eboat extends Boat{
  void move2(){
    System.out.print( "Move_Eboat" );
  }

  public static void main( String[] args ) {
    Boat a = new Boat();
    Boat b = new Eboat();
    Eboat c = new Eboat();
    b.move();
    c.move2();
    b.set(1);
    a.set(2);
    Boat[] d = new Boat[5];
    d[1] = new Eboat();
    d[1].move();
  }
}
