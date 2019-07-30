package desigpattern.decorate;
public class TestDocorate{
  public static void main(String[] args) {
    Dog d = new BlackDog("Dong");
    d.bark();
    d.describe();
    Dog d2 = new DongDog(d);
    d2.bark();
    d2.describe();
  }
}
