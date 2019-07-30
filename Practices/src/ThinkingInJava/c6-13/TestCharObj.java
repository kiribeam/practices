public class TestCharObj{
  public static void main(String[] args){
    char[] chars = new char[10];
    Class c = chars.getClass();
    System.out.println("Class is " + c.getName());
  }
}
