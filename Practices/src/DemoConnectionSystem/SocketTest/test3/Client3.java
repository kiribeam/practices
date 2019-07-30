import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client3{
  public static final String IP = "localhost";
  public static final int PORT = 10000;

  public static void main(String[] args){
    handler();
  }

  private static void handler(){
    try{
      Socket client = new Socket(IP, PORT);
      System.out.println("Client wow");
      new Thread(new ReadHandlerThread(client)).start();
      new Thread(new WriteHandlerThread(client)).start();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}

class ReadHandlerThread implements Runnable{
  private Socket client;

  public ReadHandlerThread(Socket client){
    this.client = client;
  }
  @Override
  public void run(){
    BufferedReader in = null;
    try{
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }catch(IOException e){
      e.printStackTrace();
    }
    try{
      while(true){
        System.out.println("Server BB: " + in.readLine());
      }
    }catch(IOException e){
      e.printStackTrace();
    }finally{
      if(client != null){
        client = null;
      }
    }
  }
}

class WriteHandlerThread implements Runnable{
  private Socket client;
  public WriteHandlerThread(Socket client){
    this.client = client;
  }

  @Override
  public void run(){
    PrintWriter out = null;
    try{
      out = new PrintWriter(client.getOutputStream());
    }catch(IOException e){
      e.printStackTrace();
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try{
      while(true){
        out.println(reader.readLine());
        out.flush();
      }
    }catch(IOException e){
      e.printStackTrace();
    }
    try{
      client.close();
      reader.close();
    }catch(IOException e){
      e.printStackTrace();
    }
    out.close();
  }
}
