abstract class Shape{
  public abstract String toString();
}
class Circle extends Shape{
  public String toString(){
    return "circle";
  }
}
class Rect extends Shape{
  public String toString(){
    return "Rect";
  }
}

public class TestShape{
  public static void main(String[] args){
    Shape s = new Circle();
    Rect r = (Rect) s;
    System.out.println("" + r);
  }
}
