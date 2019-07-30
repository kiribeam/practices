import java.io.*;
public class FreezeAlien{
  public static void main(String[] args) throws Exception{
    File file = new File(".\\x");
    file.mkdirs();
    ObjectOutput out = new ObjectOutputStream(new FileOutputStream(new File(".\\x\\x.file")));
    Alien quellek = new Alien();
    out.writeObject(quellek);
  }
}
