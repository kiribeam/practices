import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyThread extends Thread{
  Socket socket = null;

  public MyThread(Socket socket) throws Exception{
    this.socket = socket;
  }

  public void run(){
    BufferedReader in = null;
    try{
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }catch(IOException ex){
      ex.printStackTrace();
    }

    while(true){
      String msg = null;
      try{
        msg = in.readLine();
      }catch(IOException ex){
        ex.printStackTrace();
      }
      System.out.println(msg);
      if(msg.equals("shutdown")){
        break;
      }
    }

    try{
      in.close();
      socket.close();
    }catch(IOException ex){
      ex.printStackTrace();
    }
  }
}
