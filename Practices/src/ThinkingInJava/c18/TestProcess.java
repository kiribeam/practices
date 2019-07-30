import java.util.*;
import java.io.*;
public class TestProcess{
  public static void main(String[] args)throws Exception{
    Runtime rt = Runtime.getRuntime();
    Process p = rt.exec("CMD /C dir");
    System.out.println("Before Redirecting");
    File f = new File("TestProcess.out");
    PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(f)));
    System.setOut(ps);
    System.out.println("After");
    InputStream is = p.getInputStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String s;
    while((s = br.readLine()) != null)
      System.out.println(s);
    System.out.println("gg");
    if(p.waitFor() !=0 ) rt.exit(1);
    System.out.println(rt.freeMemory());
    System.out.println(rt.totalMemory());
    System.out.println(rt.maxMemory());
    List<Object> list = Arrays.asList(new Object[10000000]);
    System.out.println(rt.freeMemory());
    System.out.println(rt.totalMemory());
    System.out.println(rt.maxMemory());
    ps.close();
  }
}
