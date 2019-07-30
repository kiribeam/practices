public class Clowns extends Of76{
  public static void main( String[] args ) {
    Of76[] i = new Of76[3];
    i[0] = new Clowns();
    i[1] = new Acts();
    i[2] = new Of76();
    for(int x = 0; x < 3; x++) {
      System.out.println(i[x].iMethod() + " " + i[x].getClass());
    }
  }
}
