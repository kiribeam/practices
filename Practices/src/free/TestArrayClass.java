import java.lang.reflect.*;
public class TestArrayClass{
  public static void main(String[] args)throws Exception{
    System.out.println(new char[5].getClass());
    Class<?> c = String.class;
    c.newInstance();
    System.out.println(c.getSuperclass());
    for(Method m : c.getMethods()){
      if(m.toString().indexOf("toString") != -1){
        System.out.println(m);
        System.out.println(m.invoke("hahah", new Object[]{}));
      }
    }
    Integer a = 5;
    for(Field f : a.getClass().getDeclaredFields()){
      //System.out.println(f.toString().replaceAll("\\w+\\.", ""));
      if(f.toString().indexOf("value") != -1){
        f.setAccessible(true);
        f.getInt(a);
        f.set(a, 6);
      }
    }
    System.out.println("a=" + a);
  }
}
