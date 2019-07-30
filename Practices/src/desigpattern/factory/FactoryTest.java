package desigpattern.factory;
public class FactoryTest{
  public static void main(String[] args){
    Country c1 = EF.getInstance();
    Country c2 = Zion.getInstance();
    c1.fight();
    c2.fight();
  }
}
