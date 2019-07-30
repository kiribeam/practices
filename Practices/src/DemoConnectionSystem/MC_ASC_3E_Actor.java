import java.io.*;

public abstract class MC_ASC_3E_Actor extends ProtocolActor implements Serializable{;
  public static final long serialVersionUID = 1l;
  public String getResponse(String s){
    if(s.length()<22) return null;
    if(MC_ASC_3E_Operate.hasErr(s)) return null;
    String response = MC_ASC_3E_Operate.cutHead(s);
    return response;
  }
}
