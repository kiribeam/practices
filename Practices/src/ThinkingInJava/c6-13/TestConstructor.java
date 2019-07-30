import java.util.*;
import java.lang.reflect.*;

public class TestConstructor{
  public static void main(String[] args) throws Exception{
    Class c = null;
    try{
      c = Class.forName("TestConstructor$Test1");
    }catch(Exception e){
      System.out.println("No such Class");
      System.exit(0);
    }
    Constructor[] ctors = c.getConstructors();
    for(Constructor cs: ctors)
      System.out.println(cs);
    Object obj = ctors[0].newInstance(new TestConstructor(),5);
    System.out.println(obj);
  }
  class Test1{
    int i;
    public Test1(int i){
      this.i = i;
    }
    @Override
    public String toString(){
      return "Test1 -" + i;
    }
  }
}
