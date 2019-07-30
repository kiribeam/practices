import java.util.*;

public class InD1{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String tmp = sc.nextLine();
    int[] pairs = getInput(tmp);
    int size = pairs[0];
    int change = pairs[1];
    int[] array = getInput(sc.nextLine());
    int[][] changes = new int[change][2];
    for(int i=0; i<change; i++){
      changes[i] = getInput(sc.nextLine());
    }

    boolean[] evenList = new boolean[size];
    int result = 0;
    for(int i=0; i<size; i++){
      if(array[i]%2==0) {
        evenList[i] = true;
        result += array[i];
      }
    }

    int num;
    for(int i=0; i<change; i++){
      num = changes[i][0]-1;
      if(changes[i][1]%2 != 0) {
        if(evenList[num]){
          result -= array[num];
        }else{
          result += array[num] + changes[i][1];
        }
        evenList[num] = ! evenList[num];
      }else if(evenList[num]){
        result += changes[i][1];
      }
      array[num] += changes[i][1];
      System.out.println(result + "");
    }
  }

  public static int[] getInput(String s){
    String[] strings = s.split(" ");
    int length = strings.length;
    //System.out.println(length + "");
    int[] result = new int[length];
    for(int i=0; i<length; i++)
      result[i] = Integer.parseInt(strings[i]);
    return result;
  }
}
