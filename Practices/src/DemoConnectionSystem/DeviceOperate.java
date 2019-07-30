import java.util.*;

public class DeviceOperate{
  public static boolean addVar(Device d, Var var){
    d.varList.add(0, var);
    return true;
  }
  public static boolean delVar(Device d, Var var){
    d.varList.remove(var);
    return true;
  }
  public static void  updateVarList(Device d){
    ArrayList<Var> vl = d.varList;
    NamedDataOperate.removeRepeated(vl);
  }
}
