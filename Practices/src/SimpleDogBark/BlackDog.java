/* This Dog is black...*/
public class BlackDog extends Dog{
  public BlackDog(String s) {
    super(s);
    run = new NormalRun();
    bark = new DogBark();
  }
  public void KillIt(){
    System.out.println("I don't want to live in hospital for serveral days");
  }
}
