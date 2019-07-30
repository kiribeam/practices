/* A black cat is cool*/
public class BlackCat extends Cat{
  public BlackCat(String s) {
    super(s);
    run = new NormalRun();
    bark = new CatBark();
  }
}
