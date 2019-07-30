import java.io.*;

public abstract class ProtocolActor implements Serializable{
  public static final long serialVersionUID = 1l;
  public abstract String getResponse(String s);
  public abstract String send(Var var);
}
