import java.util.*;

public class SocketDeviceOperate{
  public static boolean addVar(SocketDevice sd, Var var){
    //
    return true;
  }
  public static boolean delVar(SocketDevice sd, Var var){
    //
    return true;
  }
  public static SocketDevice CreateSocketDevice(String name,
      String IP, int PORT, Protocol protocol){
    return new SocketDevice(name, IP, PORT, protocol);
    //factory
  }
  public static boolean SetTime(SocketDevice sd, int time){
    sd.time = time;
    return true;
  }

  public static SocketDevice getSocketDevice(ProjectData pd,
      String name){
    for(SocketDevice sd : pd.socketDevices){
      if(sd.name.equals(name)) return sd;
    }
    return null;
  }
}
