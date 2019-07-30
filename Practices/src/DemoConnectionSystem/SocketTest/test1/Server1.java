
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1{
  public static void main(String [] args) throws IOException{
    ServerSocket server = new ServerSocket(10000);
    System.out.println("Server running ...");

    Socket socket = server.accept();
    BufferedReader in = null;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    while(true){
      String msg = in.readLine();
      System.out.println(msg);
      if(msg.equals("shutdown")){
        break;
      }
    }
    in.close();
  }
}
