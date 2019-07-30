public class EnumTest{
  public static void main(String[] args){
    Color color = Color.Blue;
    switch(color){
      case Red:
        System.out.println("It's red");
        break;
      case Yellow:
        System.out.println("It's Yellow");
        break;
      case Blue:
        System.out.println("It's blue");
        break;
      default:
        System.out.println("Not in three");
    }
    System.out.println("over");

  }
}
enum Color{
  Red, Yellow, Blue, Green, Black;
}
