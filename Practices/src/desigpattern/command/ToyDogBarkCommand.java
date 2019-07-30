package desigpattern.command;
public class ToyDogBarkCommand implements Command{
  private Toy toy;
  public ToyDogBarkCommand(Toy t){
    toy = t;
  }
  public void execute(){
    toy.action1();
  }
}
