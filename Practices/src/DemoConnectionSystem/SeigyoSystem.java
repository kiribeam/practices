import java.io.*;
import java.util.*;
import java.net.Socket;

public class SeigyoSystem implements Runnable{
  public ProjectData projectData;
  //not used and show it in stdout!
  public Log log;
  public SeigyoSystem(ProjectData pd){
    projectData = pd;
  }

  public static void main(String[] args){
    //default loading gui run!
    //
    //add Project data file
    //this.projectData = loadData;
    //
    //get all msg from project data
    //
    //run a new thread to set gui
    //
    //run several new threads to run main linkers

    //
    //default thread over!
  }
  @Override
  public void run(){
    init();
  }
  public void init(){
    //open a gui show now loading  
    //choose a ProjectDataFile

    //load project data
    //User Gui init;
    ArrayList<SocketDevice> socketDevices = projectData.socketDevices;
    ArrayList<Device> otherDevices = projectData.otherDevices;
    //new Thread(new SeigyoSystemGui(projectData, 500)).start();
    System.out.println(javax.swing.SwingUtilities.isEventDispatchThread());
    //database set
    //linksOn() to call all links
    socketDeviceLink(socketDevices);
    //here create a new thread to update System vars!
    try{
      System.out.println("SystemVarUpdateThreadRunning");
      new Thread(new SystemVarUpdateThread(projectData.systemDevice)).start();
    }catch(Exception ex){
      System.out.println("SystemVarUpdateThread Err");
    }
    ///here relink
    while(true){
      for(Device d : socketDevices) d.disconectReset();
      System.out.println("relink part running!");
      //here call all relinks
      socketDeviceRelink(socketDevices);
      try{
        Thread.sleep(5000);
      }catch(Exception e){
        e.printStackTrace();
        System.out.println("why here in relink sleep");
      }
    }
    //try to link all of the linkers
  }
  


  public void socketDeviceLink(ArrayList<SocketDevice> socketDevices){
    for(SocketDevice sd: socketDevices){
      sd.status = false;
      Socket socket = null;
      socketLink(sd, socket);
    }
  }
  public void socketDeviceRelink(ArrayList<SocketDevice> socketDevices){
    for(SocketDevice sd : socketDevices){
      Socket s = null;
      if(!sd.status) {
        System.out.println("Now relinking: " + sd.name +"  !" );
        socketLink(sd, s);
      }
    }
  }
  public void socketLink(SocketDevice sd, Socket socket){
    if(sd.status) return;
    try{
      socket = new Socket(sd.hostIP, sd.port);
      sd.status = true;
      new Thread(new SocketHandlerThread(socket, sd)).start();
    }catch (IOException e){
      sd.status = false;
      System.out.println("linkfailed : in creating Socket :" + sd.name);
      //e.printStackTrace();
      //log out
    }
  }
  /*public void freeSocket(Socket socket){
    try{
      if(!socket.isClosed())
        socket.close();
    }catch(IOException e){
      //e.printStackTrace();
      System.out.println("close socket err");
    }finally{
      System.out.println("finally a null socket");
      socket = null;
    }
  }*/
}
