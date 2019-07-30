import java.util.*;
public class InD2{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String line = sc.nextLine();
    String[] strings = line.split(" ");
    int length = strings.length;
    int[] array = new int[length];
    for(int i=0; i<length; i++)
      array[i] = Integer.parseInt(strings[i]);
    boolean[] changes = new boolean[length];
    for(int i=1; i<length; i++){
      if(array[i]>=array[i-1]) continue;
      else if(- array[i] >= array[i-1]){
        changes[i] = !changes[i];
        array[i] = - array[i];
        continue;
      }else {
        if(iter(array, changes, i)) continue;
        System.out.println("-1");
        return;
      }
    }
    int result=0;
    for(boolean b : changes){
      if(b) result++;
      System.out.print(b + " ");
    }
    System.out.println("" +result);
  }
  public static boolean iter(int[] array, boolean[] changes, int time){
    if(time == 0) return true;
    if(changes[time-1]) return false;
    changes[time-1] = true;
    array[time-1] = -array[time-1];
    if(time == 1) return true;
    else if(array[time-1] >= array[time -2]) return true;
    else if(array[time-1] < -array[time -2]) return false;
    System.out.println("here");
    return iter(array, changes, time-1);
  }
}
