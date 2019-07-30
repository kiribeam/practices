import java.util.*;
import java.io.*;

public class SystemVarGuiActor implements SeiteiPanelActor, Serializable{
  public static final long serialVersionUID = 1l;
  public void action(SeiteiPanel sp, ProjectData pd, ArrayList<Protocol> pl, String s){
    ArrayList<Var> systemVars = pd.systemDevice.varList;
    Var var = null;
    for(Var v: systemVars){
      if(s.equals(v.name)) var = v;
    }
    if(s.equals("Add New")) var = new SystemVar("New var", pd.systemDevice);
    else if(var == null) return;
    new SystemVarSetGui(sp, pd, (SystemVar) var).setGui();
  }
  public ArrayList<String> getButtonNameList(ProjectData pd){
    ArrayList<Var> varList = pd.systemDevice.varList;
    ArrayList<String> strings = new ArrayList<>();
    for(Var v: varList){
      strings.add(v.name);
    }
    return strings;
  }
}
