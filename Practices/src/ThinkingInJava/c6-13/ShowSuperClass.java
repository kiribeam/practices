public class ShowSuperClass{
  public static void show(Object obj){
    Class c = obj.getClass();
    re(c);
  }
  public static void re(Class c){
    printInfo(c);
    if(c.getName().equals("java.lang.Object"))
      return;
    re(c.getSuperclass());
  }
  public static void printInfo(Class cc){
    System.out.println("Class name: " + cc.getName());
  }
  public static void main(String [] args){
    show(new S3(1));

  }
}
class S1{
  ;
}
class S2 extends S1{
  ;
}
class S3 extends S2{
  public S3(int i){
    super();
  }
}
