import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2{
  public static void main(String [] args) throws Exception{
    ServerSocket server = new ServerSocket(10000);
    System.out.println("Server running ...");

    int count = 0;
    while(true){
      Socket socket = server.accept();
      MyThread mythread = new MyThread(socket);
      mythread.start();
      count++;
      System.out.println("now client count is : " + count);
    }
  }
}
