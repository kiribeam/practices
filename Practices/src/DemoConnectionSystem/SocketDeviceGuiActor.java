import java.util.*;
import java.io.*;

public class SocketDeviceGuiActor implements SeiteiPanelActor, Serializable{
  public static final long serialVersionUID = 1l;
  public void action(SeiteiPanel sp, ProjectData pd, ArrayList<Protocol> pl, String s){
    ArrayList<SocketDevice> sds = pd.socketDevices;
    SocketDevice socketDevice = null;
    for(SocketDevice sd: sds){
      if(s.equals(sd.name)) socketDevice = sd;
    }
    if(s.equals("Add New")) socketDevice = new SocketDevice("New Socket Device", "", 1000);
    else if(socketDevice == null) return;
    new SocketDeviceSetGui(sp,pd,socketDevice, new VarSetPanel(socketDevice), pl).setGui();
  }
  public ArrayList<String> getButtonNameList(ProjectData pd){
    ArrayList<SocketDevice> sds = pd.socketDevices;
    ArrayList<String> strings = new ArrayList<>();
    for(SocketDevice sd: sds){
      strings.add(sd.name);
    }
    return strings;
  }
}
