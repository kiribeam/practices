
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMC{
  public static void main(String [] args) throws IOException{
    ServerSocket server = new ServerSocket(5000);
    System.out.println("Server running ...");
    Socket socket = server.accept();
    System.out.println("get link");
    BufferedReader in = null;
    InputStream is = socket.getInputStream();
    int i;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    while(( i = is.available()) > 0){
      byte[] chars = new byte[i];
      is.read(chars, 0, i);
      String msg = new String(chars);
      System.out.println(msg);
      if(msg.equals("shutdown")){
        break;
      }
    }
    is.close();
    in.close();
  }
}
