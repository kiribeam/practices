import java.io.*;
import java.util.*;

public class SystemVar extends Var{
  public static final long serialVersionUID = 1l;
  public ArrayList<DecorateVar> varList;
  public String expression;
  //formart of Var set
  //$0 0000 02 I A
  //from the first Var get 2 char from 0000offset and parse it to int
  //the number is defined as Acsii value

  public SystemVar(String name, SystemDevice sd){
    super(name);
    amount = 0; //0-String 1-Int 2-Double
    type = 4;
    device = sd;
    varList = new ArrayList<>();
    expression = "";
    addr= "";
  }

  public void addVar(DecorateVar dv){
    if(! varList.contains(dv)){
      varList.add(dv);
//      amount ++;
    }
  }

  public void removeVar(DecorateVar dv){
    if(varList.contains(dv)) {
      varList.remove(dv);
 //     amount --;
    }
  }
  public DecorateVar getVar(int i){
    return varList.get(i);
  }

}
