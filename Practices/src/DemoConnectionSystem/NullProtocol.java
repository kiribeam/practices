public class NullProtocol extends Protocol{
  public static final long serialVersionUID = 1L;
  public NullProtocol(String name){
    super(name, false, 4, 0, "\n");
    actor = new NullProtocolReadActor();
  }
  @Override
  public void changeActor(Var var){
    if(0 == var.type) actor = new NullProtocolReadActor();
  }
  @Override
  public int frameLength(String s){
    return -1;
    //it shows not a determined length!
    //not used in this
    //I'can make a Operate class to call the function!
  }

}
