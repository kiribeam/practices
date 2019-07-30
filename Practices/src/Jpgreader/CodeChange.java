import java.io.*;
import java.util.*;
public class CodeChange{
  private static boolean flag = false;
  //the flag which used to clean up ff
  private File file;
  //the file I want to read
  public CodeChange(File f) {
    file = f;
  }
  //test drive
  public static void main(String[] args) {
    ArrayList<Integer> m1,m2;
    File file = new File("yuki.jpg");
    TwoList two = new CodeChange(file).readFile();
  //  TwoList two = CodeChange.readFile(new File(args));
    m1 = two.m1;
    m2 = two.m2;
    System.out.println(m1);
    System.out.println(m2);
    try {
      System.out.println(two.getMessage());
    }catch (Exception e) {
      ;
    }
  }
  // to read a JPG file and return a message list
  public TwoList readFile(){
    //file = new File( "box1.JPG" );
    TwoList two = new TwoList();
  try{
      InputStream in = new FileInputStream(file);//use a InputStream to read a byte
      two = getFF(in);//get the file message
     in.close();
     }  catch (Exception e) {
      ;
    }
   return two;
  }
  //to dodge the multiple FF in file and read message
  public TwoList getFF(InputStream in) throws Exception{
    int s ;
    TwoList two = new TwoList();
    TwoList temp = new TwoList();
    //there may be several file message in a jpg picture
    //I choose the biggest size
    int counter = 0;
    while ((s = in.read()) != -1) {
      counter++;
      if(counter > 20000) break;
      //counter used to stop read file
      if(s == 0xff){
        flag = true;//ff is a control tag
        continue;
      } else {
        if(flag && s == 0xe0) {
          flag=false;
          temp.m1 = fillArray(in);
          if(two.m1 == null) {
            two.m1=temp.m1;
          }else if(two.m1.get(9) == 0) {
            two.m1=temp.m1;
          }// the message behind ffe0
        }else if(flag && s==0xc0) {
          flag = false;
          temp.m2 = fillArray(in);
          if(two.m2 == null) {
            two.m2=temp.m2;
          }else if(two.m2.get(3) < temp.m2.get(3)) {
            two.m2=temp.m2;
          }//the message behind ffc0
        } else {
          flag = false;
        }
      }
      //  System.out.println(Integer.toHexString(s));
    }
    return two;
  }
  //to read the control message's size and read equal bytes
  public ArrayList<Integer> fillArray( InputStream in){
    ArrayList<Integer> m = new ArrayList<>();
    try{
      int temp1 = in.read();
      int temp2 = in.read();
      m.add(temp1);
      m.add(temp2);
      for(int i=2;i<temp1*256+temp2;i++){
        m.add(in.read());
      }
    } catch(Exception e) {
      ;
    }
    return m;
  }
}
// two ArrayList to combine a structure 
class TwoList{
  ArrayList<Integer> m1;
  ArrayList<Integer> m2;
  int[] m= new int[6];
  public int[] getMessage() throws Exception{
    m[0] = m1.get(7) * 256+ m1.get(8);// version 
    m[1] = m1.get(9);  //density mode 0:null 1: per inch 2: per cm
    m[2] = m1.get(10) * 256 + m1.get(11); // x density
    m[3] = m1.get(12) * 256 + m1.get(13); // y density
    m[4] = m2.get(3) * 256 + m2.get(4); // height
    m[5] = m2.get(5) * 256 + m2.get(6);//width
    for(int i=0;i<m.length;i++){
      System.out.println(m[i]);
    }
    return m;
 }
}
