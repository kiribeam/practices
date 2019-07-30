import java.util.ArrayList;

public class SocketDevice extends Device {
  public String hostIP;
  public int port;
  public static final long serialVersionUID = 1l;

  public SocketDevice(String name, String ip, int port){
    super(name);
    type = 1;
    hostIP = ip;
    this.port = port;
  }

  public SocketDevice(String name ,String ip, int port, Protocol protocol){
    this(name, ip, port);
    this.protocol = protocol;
  }

}
