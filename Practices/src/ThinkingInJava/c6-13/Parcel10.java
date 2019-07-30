public class Parcel10{
  public Destination destination(final String dest, final float price){
    return new Destination(){
      private int cost;
      {
        cost = Math.round(price);
        if(cost > 100)
          System.out.println("Over budget");
      }
      private String label = dest;
      public String readLabel(){return label;}
    };
  }
  public static void main(String[] a){
    Parcel10 p = new Parcel10();
    Destination d = p.destination("ss ", 101.395F);
  }
}

interface Destination{
  String readLabel();
}
