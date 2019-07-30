import java.io.*;
import java.net.*;

public class Turnnel{
  public final int PORT;
  public final String PLC_IP;
  public final int PLC_PORT;

  public Turnnel(int p, String ip, int p2){
    PORT = p;
    PLC_IP = ip;
    PLC_PORT = p2;
  }

  public static void main(String[] args){
    try{
      int p = Integer.parseInt(args[0]);
      String ip = args[1];
      int p2= Integer.parseInt(args[2]);
      new Turnnel(p, ip, p2).init();
    }catch (Exception e){
      System.out.println("Not  a Good Input! \n Using PORT IP PORT");
      System.exit(1);
    }
  }
  public void init(){
    ServerSocket socketServer = null;
    Socket socketIn = null;
    Socket socketOut = null;


    try{
      socketServer = new ServerSocket(PORT);
      socketIn = socketServer.accept();
      socketOut = new Socket(PLC_IP, PLC_PORT);

    }catch(Exception e){
      e.printStackTrace();
    }

    try{
      BufferedReader brFromKv = new BufferedReader(new InputStreamReader(socketIn.getInputStream()));
      BufferedReader brFromPlc = new BufferedReader(new InputStreamReader(socketOut.getInputStream()));
      PrintWriter pwToPlc = new PrintWriter(socketOut.getOutputStream());
      PrintWriter pwToKV = new PrintWriter(socketIn.getOutputStream());

      while(true){
        String msg = brFromKv.readLine();
        System.out.println("MSG is : "  + msg);
        if(msg.equals("?K")) {
          pwToKV.write("52\r\n");
          pwToKV.flush();
          continue;
        }
        pwToPlc.write(msg + "\r\n");
        pwToPlc.flush();
        msg = brFromPlc.readLine();
        pwToKV.write(msg + "\r\n");
        pwToKV.flush();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
