import java.util.*;

public abstract class NamedDataOperate{
  public static void removeRepeated(ArrayList<? extends NamedData> al){
    int length = al.size();
    NamedData tmp, tmp2;
    for(int i=0; i<length; i++){
      tmp = al.get(i);
      for(int j=i+1; j<length; j++){
        tmp2 = al.get(j);
        if(tmp.getName().equals(tmp2.getName()))
          tmp2.name = "Null";
      }
    }
    int i=0;
    while(i<al.size()){
      NamedData nd = al.get(i);
      if(nd.getName().equals("Null") || nd.getName().equals("Add New")){
        al.remove(nd);

      }else{
        i++;
      }
    }
  }
  public static NamedData getNamedData(ArrayList<? extends NamedData> al, String name){
    for(NamedData nd: al){
      if(nd.name.equals(name)) return nd;
    }
    return null;
  }

  public static String[] getNamedDataNames(ArrayList<? extends NamedData> al){
    int n = al.size();
    String[] names = new String[n];
    for(int i=0; i<n; i++){
      names[i] = al.get(i).name;
    }
    return names;
  }
}
