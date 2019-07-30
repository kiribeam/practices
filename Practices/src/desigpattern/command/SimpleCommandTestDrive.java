package desigpattern.command;
public class SimpleCommandTestDrive{
  public static void main(String[] args){
    Toy toyDog = new ToyDog();
    SimpleToyControl remocon = new SimpleToyControl();
    Command barkCommand = new ToyDogBarkCommand(toyDog);
    Command runCommand = new ToyDogRunCommand(toyDog);
    remocon.setCommand(barkCommand);
    remocon.buttonPressed();
    remocon.setCommand(runCommand);
    remocon.buttonPressed();
  }
}
