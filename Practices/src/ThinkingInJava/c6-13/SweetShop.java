public class SweetShop{
  public static void main(String[] args){
    System.out.println("Inside Main:");
    new Candy();

    System.out.println("After creating candy");
    try{
      Class.forName("Gum");
    }catch(Exception ex){
      System.out.println("Couldn't found Gum");
    }

    System.out.println("After Class.forName(\"Gum\")");
  }
}
class Candy{
  static {
    System.out.println("loading candy");
  }
}
class Gum{
  static {
    System.out.println("loading gum");
  }
}
