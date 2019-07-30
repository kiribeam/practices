import java.io.*;
import java.net.*;

public class LocalProxyDemo{
  public static void main(String[] args){
    ServerSocket server = null;
    try{
      server = new ServerSocket(6666);
    }catch(Exception e){
      System.out.println("Create Server failed1");
      System.exit(1);
    }
    while(true){
      try{
        Socket socket = server.accept();
        ProxyHandler handler = new ProxyHandler(socket);
        handler.run();
      }catch(Exception e){
        System.out.println("Wrong in main thread");
      }
    }
  }
}
class ProxyHandler implements Runnable{
  Socket socket = null;
  BufferedReader breader = null;
  PrintWriter pwriter = null;

  public ProxyHandler(Socket socket){
    this.socket=socket;
  }

  @Override
  public void run(){
    if(socket == null) return;
    try{
      breader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      pwriter = new PrintWriter(socket.getOutputStream());
    }catch(IOException e){
      System.out.println("Get Stream failed!");
      return;
    }
    String line = "";
    while(true){
      try{
        while((line = breader.readLine())!=null){
          System.out.println(line);
        }
        pwriter.println("HTTP/1.1 200 OK\r\n\r\n" + 
            "<html><body><p>Hello world</p></body></html>\r\n");
        pwriter.flush();
        System.out.println("Print OK");
      }catch(Exception e){
        System.out.println("Read line failed");
      }
    }
  }
}
