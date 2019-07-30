//kiri at 07.19 sui
import java.util.*;


public class Queen {

  static ArrayList<int[]> result= new ArrayList<>();
  static int[] temp = new int[8];
  static int count = 0;

  public static void queen(int n){
    count++;
    if(n>7) {
      result.add(temp.clone());
      return;
    }
    for(int i=1; i<9; i++){
      boolean flag = false;
      for(int j=0; j<n; j++){
        if((temp[j] == i) || (temp[j] == i-(n-j)) || (temp[j] == i+(n-j))){
          flag = true;
          break;
        }
      }
      if(!flag) {
        temp[n] = i;
        queen(n+1);
        flag = true;
      } else {
        continue;
      }
    }
    temp[n] = 0;
  }
  public static void main(String[] args){
    queen(0);
    System.out.println(result.size());
    int[] b = result.get(1);
    String s = "";
    for(int i: b){
      s = s+i+" ";
    }
    System.out.println(s);
    System.out.println("Run "+ count + " times!");
  }
}
