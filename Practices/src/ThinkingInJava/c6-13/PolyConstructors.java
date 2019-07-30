class Glyph{
  void draw(){
    System.out.println("Gly.draw");
  }
  Glyph(){
    System.out.println("before gly draw");
    this.draw();
    System.out.println("after gly draw");
  }
}

class RoundGlyph extends Glyph{
  private int radius = 1;
  RoundGlyph(int r){
    radius = r;
    System.out.println("RoundGlyph.r, radius = " + radius);
  }
  void draw(){
    System.out.println("Round.draw() , radius =" + radius);
  }
}

public class PolyConstructors{
  public static void main(String[] args){
    new RoundGlyph(5);
  }
}
