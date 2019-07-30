import java.util.*;
import java.io.*;

public abstract class RandomAccessProtocolActor extends ProtocolActor implements Serializable{
  public static final long serialVersionUID = 1l;
  public abstract String packedSend(ArrayList<Var> varList);
  public abstract ArrayList<String> getPackedResponse(String s);
}
