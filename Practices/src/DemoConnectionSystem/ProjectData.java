import java.util.ArrayList;
import java.util.Hashtable;

import java.io.*;

public class ProjectData implements Serializable{
  public String projectName;
  public static final long serialVersionUID = 1L;
  public SystemDevice systemDevice = new SystemDevice();
  public ArrayList<DataBase> dataBases = new ArrayList<>();
  public ArrayList<SocketDevice> socketDevices = new ArrayList<>();
  public ArrayList<Device> otherDevices = new ArrayList<>();
  //public ArrayList<Protocol> protocols = new ArrayList<>();
  public ArrayList<GuiPage> guiPages = new ArrayList<>();
  //public Hashtable<String, Var> readTable = new Hashtable<>();
  //public Hashtable<String, Var> writeTable = new Hashtable<>();
  //public Hashtable<String, Var> dataTable = new Hashtable<>();

  public ProjectData(String name){
    projectName = name;
  }
  public void update(){
    updateSocketDevice();
    updateOtherDevice();
    updateGuiPage();
    updateSystemDevice();
    updateAllVar();
  }
  public void updateSocketDevice(){
    NamedDataOperate.removeRepeated(socketDevices);
  }
  public void updateOtherDevice(){
    NamedDataOperate.removeRepeated(otherDevices);
  }
  public void updateGuiPage(){
    NamedDataOperate.removeRepeated(guiPages);

  }
  public void updateAllVar(){

  }
  public void updateSystemDevice(){
    NamedDataOperate.removeRepeated(systemDevice.varList);
  }
}
