package desigpattern.command;
public class SimpleToyControl{
  private Command command;
  public void setCommand(Command c){
    command = c;
  }
  public void buttonPressed(){
    if(command == null) {
      System.out.println("No command");
      return;
    }
    command.execute();
  }
}
