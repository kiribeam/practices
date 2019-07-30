import java.net.*;
import java.util.concurrent.*;
import java.io.*;

public class CloseResource{
  public static void main(String[] args)throws Exception{
    ExecutorService exec = Executors.newCachedThreadPool();
    ServerSocket server = new ServerSocket(8080);
    InputStream socketInput = new Socket("localhost", 8080).getInputStream();
    exec.execute(new IOBlocked(socketInput));
    exec.execute(new IOBlocked(System.in));
    TimeUnit.MILLISECONDS.sleep(100);
    System.out.println("Shutting down all Threads");
    exec.shutdownNow();
    TimeUnit.SECONDS.sleep(1);
    System.out.println("Closing " + socketInput.getClass().getName());
    socketInput.close();
    TimeUnit.SECONDS.sleep(1);
    System.out.println("Closing " + System.in.getClass().getName());
    System.in.close();
  }
}
