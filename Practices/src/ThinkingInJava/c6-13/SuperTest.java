class Super{
  public int f = 0;
  public int getf(){
    return f;
  }
}

class Sub extends Super{
  public int f = 1;
  public int getf(){
    return f;
  }
  public int getsf(){
    return super.f;
  }
}

public class SuperTest{
  public static void main(String[] args){
    Super sp = new Sub();
    System.out.println("sp's f is " + sp.f + ", sp.getf is " + sp.getf());
  }
}
