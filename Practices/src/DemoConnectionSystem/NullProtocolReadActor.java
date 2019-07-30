import java.io.*;

public class NullProtocolReadActor extends NullProtocolActor implements Serializable{
  public static final long serialVersionUID = 1l;
  @Override
  public String send(Var var){
    return ("READ" + var.addr + "BYTE" + "\n");
  }
  @Override
  public String getResponse(String s){
    return s.substring(4,8);
  }
}
