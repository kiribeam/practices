import java.util.*;
import java.io.*;
import java.net.*;

public class TestServerMC{
  private final int PORT;
  public Protocol protocol;
  public TestServerMC(int port, Protocol protocol){
    this.PORT = port;
    this.protocol = protocol;
  }
  public void serverRun(){
    ServerSocket serverSocket = null;
    Socket socket = null;
    try{
      serverSocket = new ServerSocket(PORT);
      while(true){
        socket = serverSocket.accept();
        new Thread(new ServerThread(socket)).start();
      }
    }catch(Exception ex){
      ex.printStackTrace();
      System.out.println("in socket create");
    }
  }
  public class ServerThread implements Runnable{
    public Socket socket;
    public ServerThread(Socket socket){
      this.socket = socket;
    }
    public void run(){
      InputStream is = null;
      InputStreamReader isr = null;
      BufferedReader br = null;
      OutputStream os = null;
      PrintWriter pw = null;
      try{
        is = socket.getInputStream();
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        os = socket.getOutputStream();
        pw = new PrintWriter(os);
      }catch(IOException ex){
        ex.printStackTrace();
        System.out.println("get server stream failed");
      }
      String tmp = "";
      Protocol mc = new MC_ASC_3E_Protocol();
      while(true){
        try{
          char[] head = new char[mc.headLength];
          br.read(head, 0, mc.headLength);
          tmp += new String(head);
          int length = mc.frameLength(tmp);
          char[] frame = new char[length];
          br.read(frame, 0, length);
          tmp += new String(frame);
          //System.out.println("tmp is " + tmp);
        }catch(Exception e){
          e.printStackTrace();
          System.out.println("Read err in readLine");
        }
        tmp = "01234567890ABC";
        int length = tmp.length() + 4;
		String s = Integer.toHexString(length);
		while(s.length()<4){
		  s = "0" + s;
		}
        tmp = "D000" + "00ff" + "03FF" +"00" + s + "0000" + tmp;
        System.out.println(" tmp is " + tmp);
        try{
          pw.write(tmp);
          pw.flush();
        }catch(Exception e){
          e.printStackTrace();
          System.out.println("server write err");
        }
      }
    }
  }
  public static void main(String[] args){
    System.out.println("server port: " + Integer.parseInt(args[0]));
    TestServerMC t1 = new TestServerMC(Integer.parseInt(args[0]), new MC_ASC_3E_Protocol());
    t1.serverRun();
  }
}
