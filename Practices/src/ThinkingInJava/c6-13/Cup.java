class Cups{
  Cups(int i){
    System.out.println("Cup " + i + "");
  }
}

class Cup{
  static Cups cup1;
  static Cups cup2;
  Cup(){
    System.out.println("Cups");
  }
  static {
    cup1 = new Cups(1);
    cup2 = new Cups(2);
  }
}
