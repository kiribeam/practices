import java.util.*;
import java.io.*;
import java.net.*;

public class TestServer{
  private final int PORT;
  public Protocol protocol;
  public TestServer(int port, Protocol protocol){
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
      while(true){
        try{
          tmp = br.readLine();
          System.out.println("tmp is " + tmp);
        }catch(Exception e){
          e.printStackTrace();
          System.out.println("Read err in readLine");
        }
        tmp = protocol.getResponse(tmp);
        System.out.println(" tmp is " + tmp);
        if(tmp.equals("0001")){
          tmp = "RTEN" + 6666 + "\n";
        }else if(tmp.equals("0002")){
          int i = PORT/2;
          tmp = "RTEN" + i + "ZZZ\n";
        }else{
          tmp = "RTEN" + "-ERR\n";
        }
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
    TestServer t1 = new TestServer(Integer.parseInt(args[0]), new NullProtocol("test"));
    t1.serverRun();
  }
}
