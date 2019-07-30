import java.util.*;

public class VapireNumber{
  public static ArrayList<NumberPair> numbers = new ArrayList<>();
  public static int total=0;
  public static int[][] getNumber(){
    int n=total;
    if(n == 0){
      numbers=calculate();
      total=numbers.size();
      n=total;
    }
    int[][] result = new int[n][3];
    Iterator<NumberPair> it = numbers.iterator();
    NumberPair tmp = numbers.get(0);
    for(int i=0; i<n; i++){
      tmp = it.next();
      result[i][0] = tmp.pair[0];
      result[i][1] = tmp.pair[1];
      result[i][2] = result[i][0] * result[i][1];
    }
    return result;
  }
  public static boolean testNumber(int n1, int n2){
    int a = n1 * n2;
    if(a<1000) return false;
    int[] n = new int[4];
    n[0] = n1 % 10;
    n[1] = n1 / 10;
    n[2] = n2 % 10;
    n[3] = n2 / 10;
    int count = 0;
    for(int i=0; i<4; i++){
      if((a%10) == n[i]){
        count++;
        if(count == 4) return true;
        a/=10;
        n[i] = -1;
        i= -1;
      }
    }
    return false;
  }
  public static ArrayList<NumberPair> calculate(){
    ArrayList<NumberPair> result = new ArrayList<>();
    for(int i=10; i<100; i++){
      for(int j=10; j<=i; j++){
        if((i%10 == 0) && (j%10 == 0))
          continue ;
        if(testNumber(i, j)){
          NumberPair tmp = new NumberPair();
          tmp.pair[0] = i;
          tmp.pair[1] = j;
          result.add(tmp);
        }
      }
    }
    return result;
  }
  public static void main(String[] args){
    int[][] lists = VapireNumber.getNumber();
    for(int[] p: lists){
      System.out.println("[ " + p[0] + " , " + p[1] + " , " + p[2] + "]");
    }

  }
  private static class NumberPair{
    int[] pair = new int[2];
  }
}
