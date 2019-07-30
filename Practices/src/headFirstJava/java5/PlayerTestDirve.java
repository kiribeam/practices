public class PlayerTestDirve {
  static int size = 5;
  public static void main( String[] args ) {
    System.out.println(Player.playerCount);
    Player one = new Player("Tiger Woods");
    System.out.println(Player.playerCount);
    PlayerTestDirve x = new PlayerTestDirve();
    x.p();
  }
  public void p(){
    System.out.println("size is " + size);
  }
}
