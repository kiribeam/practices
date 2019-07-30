public class Parcel4{
  private int i = 5;
  public void getI(){
    System.out.println(" i = " + i);
  }
  private void change(){
    this.i += 2;
  }
  
  class Model{
    private int j = 10;
    public void innerChange(){
      i++;
    }
    public void change2(){
      change();
    }
  }
  public Model getInner(){
    Model m = new Model();
    return m;
  }
  public void changeI(){
    Model m = new Model();
    m.j ++;
    System.out.println("m.j == " + m.j);
  }
  public static void main(String[] args){
    Parcel4 p = new Parcel4();
    p.getI();
    Parcel4.Model pi = p.getInner();
    pi.change2();
    p.getI();
    Parcel4.Model pi2 = p.getInner();
    pi2.change2();
    p.getI();

    System.out.println(" \n   ");
    p.changeI();
  }
}
