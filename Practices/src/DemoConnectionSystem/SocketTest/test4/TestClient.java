
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;
import java.net.Socket;

public class TestClient{
  public static void main(String[] args) throws Exception{
    System.out.println("Client on ..");
    Socket socket = new Socket("192.168.0.10", 5000);
    //Socket socket  = new Socket("192.168.0.10",8501);
    PrintWriter out = new PrintWriter(socket.getOutputStream());
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    InputStream is = socket.getInputStream();


    String msg = "500000FF03FF000018001004010000D*0000000001";
    //String msg = "?K" + ((char) 0b00001101);
    out.println(msg);
    out.flush();
    Thread.sleep(500);
    int i = is.available();
    byte[] b = new byte[i];
    is.read(b);
    System.out.println(new String(b));
    socket.close();
    out.close();
    reader.close();
  }
}
