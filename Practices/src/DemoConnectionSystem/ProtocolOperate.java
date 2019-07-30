import java.util.*;

public class ProtocolOperate{
  public static ArrayList<Protocol> getProtocolList(){
    ArrayList<Protocol> protocolList = new ArrayList<>();
    protocolList.add(new NullProtocol("null"));
    protocolList.add(new MC_ASC_3E_Protocol());
    // user config
    //
    return protocolList;
  }
  public static void updateProtocol(){}
}
