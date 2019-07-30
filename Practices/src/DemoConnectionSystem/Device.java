import java.util.ArrayList;
import java.util.Hashtable;
import java.io.*;

public abstract class Device extends NamedData implements Serializable{
  public static final long serialVersionUID = 1L;
  public int type;
  public ArrayList<Var> varList;
  public Protocol protocol;
  public int time;
  public boolean status;

  public Device(String name){
    this.name = name;
    varList = new ArrayList<>();
    protocol = new NullProtocol("null");
    time = 200;
    status = true;
  }
  public void addVar(Var var){
    varList.add(var);
  }
  public void delVar(String varName){
    for(Var v: varList){
      if(v.name.equals(varName))
        varList.remove(v);
    }
  }
  public void delVar(Var var){
    varList.remove(var);
  }
  public void disconectReset(){
    if(status) return;
    for(Var v: varList){
      v.value = "NULL";
    }
  }
}
