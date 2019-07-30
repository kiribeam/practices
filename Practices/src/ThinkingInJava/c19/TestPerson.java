public class TestPerson{
  public String name;
  public String getName(){
    return name;
  }
  public static void main(String [] args){
    TestPerson p = new TestMan();
    p.name = "PP";
    System.out.println(p.name);
    System.out.println(p.getName());
    System.out.println(((TestMan) p).getSuperName());
  }
}

class TestMan extends TestPerson{
  public String name;
  public String getName(){
    return name;
  }
  public String getSuperName(){
    return super.name;
  }
}
