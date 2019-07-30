import java.io.*;

public class Blip3 implements Externalizable{
  public static final long serialVersionUID =1l;
  private int i;
  private String s;
  private String fuck = "New fuck";

  public Blip3(){
    System.out.println("Blip3 constructor");
  }

  public Blip3(String x, int a){
    System.out.println("blip3 intx a");
    s = x;
    i = a;
    fuck = "new new fuck";
  }

  public String toString(){
    return s + i + fuck;
  }


  public void writeExternal(ObjectOutput out)throws IOException{
    System.out.println("Blip3.writeExternal");
    //out.writeObject(s);
    out.writeInt(i);
  }
  public void readExternal(ObjectInput in)throws IOException, ClassNotFoundException{
    System.out.println("blip3read");
    //s= (String) in.readObject();
    i = in.readInt();
  }

  public static void main(String[] args)throws Exception{
    System.out.println("constructing objects");
    Blip3 b3 = new Blip3("A String", 47);
    System.out.println(b3);
    ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blip3.out"));
    System.out.println("saving");
    o.writeObject(b3);
    o.close();
    ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blip3.out"));
    System.out.println("Recovering b3");
    b3 = (Blip3) in.readObject();
    System.out.println(b3);

  }
}
