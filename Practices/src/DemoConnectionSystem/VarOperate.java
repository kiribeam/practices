import java.util.*;

public class VarOperate{
  public static void removeUseless(ArrayList<Var> al){
    for(Var v: al){
      if(v.type > 10) {
        v.name = "Null";
        v.value = "";
      }
    }
    NamedDataOperate.removeRepeated(al);
  }
}
