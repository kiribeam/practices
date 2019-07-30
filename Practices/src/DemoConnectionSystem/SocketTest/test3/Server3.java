import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3{
  public static final int PORT = 10000;

  public static void main(String[] args){
    System.out.println("server begin");
    Server3 server = new Server3();
    server.init();
  }

  public void init(){
    ServerSocket serverSocket = null;
    try{
      serverSocket = new ServerSocket(PORT);
      while(true){
        Socket client = serverSocket.accept();
        new Thread(new ReadHandlerThread(client)).start();
        new Thread(new WriteHandlerThread(client)).start();
      }
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      try{
        if(serverSocket != null)
          serverSocket.close();
      }catch(IOException e){
        e.printStackTrace();
      }
    }
  }
}

