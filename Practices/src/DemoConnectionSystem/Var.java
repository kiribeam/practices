import java.io.*;

public class Var extends NamedData implements Serializable{
  public static final long serialVersionUID = 1l;
  int amount;
  int type; //read write command tempread system no_used
  String addr;// format "Reg/bits/addr"
  String value;
  Device device;
  String comment;
  public Var(String name){
    this.name = name;
    amount = 1;
    type = 5;
    value = "";
    device = null;
    comment = "";
  }
  public void update(Var var){
    this.value = var.value;
  }
  public void update(String s){
    value = s;
  }
}
