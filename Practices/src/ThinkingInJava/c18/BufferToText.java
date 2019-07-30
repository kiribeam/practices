import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;

public class BufferToText{
  public static void main(String[] args) throws Exception{
    FileChannel fc = new FileOutputStream("data2.txt").getChannel();
    ByteBuffer buff = ByteBuffer.allocate(24);
    buff.asCharBuffer().put("Some Text");
    fc.write(buff);
    fc.close();
    fc = new FileInputStream("data2.txt").getChannel();
    buff.clear();
    fc.read(buff);
    buff.flip();
    System.out.println(buff.asCharBuffer());
  }
}
