class Toy {
  //Toy(){}
  Toy(int i){}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots{
  FancyToy(){
    super(1);
  }
}

interface HasBatteries{}
interface Waterproof{}
interface Shoots{}

public class ToyTest{
  static void printInfo(Class cc){
    System.out.println("Class name : " + cc.getName() + "is interface? " + cc.isInterface());
    System.out.println("Simple name: " + cc.getSimpleName());
    System.out.println("Canonial name :" + cc.getCanonicalName());
  }
  public static void main(String[] args){
    Class c = null;
    try{
      c = Class.forName("FancyToy");
    }catch(Exception ex){
      System.out.println("Can't found FancyToy");
      System.exit(1);
    }
    printInfo(c);
    for(Class face : c.getInterfaces())
      printInfo(face);
    Class up = c.getSuperclass();
    Object obj = null;
    try{
      obj = up.newInstance();
    }catch(InstantiationException e){
      System.out.println("Can't instantiate");
      System.exit(1);
    }catch(IllegalAccessException ex){
      System.out.println("Can't access");
      System.exit(1);
    }
    printInfo(obj.getClass());
  }
}
