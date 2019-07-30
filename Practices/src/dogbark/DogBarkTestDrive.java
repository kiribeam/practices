package dogbark;
public class DogBarkTestDrive{
  public static void main(String[] args) {
    Dog dong = new BlackDog("dong");
    System.out.println(dong.getName());
    dong.bark();
  }
}
