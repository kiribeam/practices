
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client1{
  public static void main(String[] args) throws Exception{
    System.out.println("Client on ..");
    Socket socket = new Socket("127.0.0.1", 10000);
    PrintWriter out = new PrintWriter(socket.getOutputStream());
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while(true){
      String msg = reader.readLine();
      out.println(msg);
      out.flush();
      if(msg.equals("shutdown")){
        break;
      }
    }
    socket.close();
    out.close();
    reader.close();
  }
}
