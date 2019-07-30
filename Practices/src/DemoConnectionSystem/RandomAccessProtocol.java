import java.io.*;
import java.util.*;

public abstract class RandomAccessProtocol extends Protocol implements Serializable{
  public RandomAccessProtocol(String name, int hl, int et, String es){
    super(name, true, hl, et, es);
  }
  public static final long serialVersionUID = 1L;
  public abstract String packedSend(ArrayList<Var> varList);
  public abstract ArrayList<String> getPackedResponse(String s);
  //use Vartype to Reload diffient Send Or use protocol Actor to use status Pattern!
  //use different Actor to define different SendCode!
  //hahaha hitotsu no Interface de ii no!
  //mazuha { public abstract ??? send(???)  }{public ab .. resposeAnalyse ???}
  //soside Define actor to change status by Var type to give a general way!
 //public abstract Var analyse(String s, Var v);
  //public abstract String getReadResponse(String s);
  //public abstract String getWriteResponse(String s);
  //public abstract String packedSendRead(ArrayList);//Type ha mada soutei site nai!
  //packdedSendWrite
  //public abstract String analyse(String s);// Ato de syu-sei suru!
}
