package desigpattern.factory;
public abstract class Country{
  private String name;
  private MSFactory factory;
  public Country(String s, MSFactory f) {
    name = s;
    factory = f;
  }
  public MS createMS(){
    return factory.create();
  }
  public abstract void fight();
}
