import java.util.*;
class Building{}

class House extends Building{}

public class ClassTypeCapture<T>{
  Class<T> kind;
  Map<String, Class<?>> map;
  public ClassTypeCapture(Class<T> kind){
    this.kind = kind;
    map = new HashMap<>();
  }

  public boolean f(Object arg){
    return kind.isInstance(arg);
  }
  public void addType(String type, Class<?> kind){
    map.put(type, kind);
  }
  public Object creatNew(String typename) throws Exception{
    Class<?> c = map.get(typename);
    return c.newInstance();
  }
  public static void main(String[] args){
    ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<Building>(Building.class);
    System.out.println(ctt1.f(new Building()));
    System.out.println(ctt1.f(new House()));

    ClassTypeCapture<House> ctt2 = new ClassTypeCapture<House>(House.class);
    System.out.println(ctt2.f(new Building()));
    System.out.println(ctt2.f(new House()));

    
  }
}
