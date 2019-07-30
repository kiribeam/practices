import java.io.*;
import java.util.*;

public abstract class Protocol extends NamedData implements Serializable {
  public final String name;
  public final boolean randomFlag;
  //now it has no use!
  public ProtocolActor actor;
  public ArrayList<ProtocolActor> actorList;
  public final int headLength; 
  //to read basic length a temp try, not only the so-called head length
  public final int endType;
  //end with decided length 1 , end with special chars 0;
  public final String endString;
  public static final long serialVersionUID = 1L;
  public Protocol(String n, boolean flag, int hl, int et, String es){
    name = n;
    randomFlag = flag;
    headLength = hl;
    endType = et;
    endString = es;
  }
  public String send(Var var){
    changeActor(var);
    return actor.send(var);
  }
  public String getResponse(String s){
    return actor.getResponse(s);
  }
  public abstract void changeActor(Var var);
  public abstract int frameLength(String s);
  //return the rest length without head!
  //to use this, if it can assert the number of rest chars of one frame, return number.
  //if end with "\n" or "\r\n", return -1
  //if can't get it , return -2;
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
  @Override
  public String toString(){
    return name;
  }
}
