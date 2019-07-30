package desigpattern.command;
public class ToyDogRunCommand implements Command{
  private Toy toy;
  public ToyDogRunCommand(Toy t){
    toy = t;
  }
  public void execute(){
    toy.action2();
  }
}
