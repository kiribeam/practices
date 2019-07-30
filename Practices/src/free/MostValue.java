import java.util.*;
public class MostValue{
  public static int max = 0;
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int weight = sc.nextInt();
    int n = sc.nextInt();
    int[][] input = new int[n][2];
    for(int i=0; i<n; i++){
      input[i][0] = sc.nextInt();
    }
    for(int i=0; i<n; i++){
      input[i][1] = sc.nextInt();
    }
    sc.close();
    System.out.println("result is:" + solve(input, weight));
  }
  public static int solve(int[][] input, int weight){
    Arrays.sort(input, 
        (int[] a, int[] b) ->
        {return a[0]-b[0] == 0 ? b[1]-a[1] : a[0]-b[0];});
    for(int[] ar : input){
      System.out.println(Arrays.toString(ar));
    }
    int next = 1;
    for(;next<input.length && input[next][0]==input[0][0]; next++);
    dfs(input, 0, next, weight, 0);
    return max;
  }
  public static void dfs(int[][] input, int index, int next, int weight, int value){
    int length = input.length;
    if(index == length || input[index][0] > weight){
      max = Math.max(max,value);
      return;
    }
    if(index == next){
      for(; next<length && input[next][0] == input[index][0]; next++);
    }
    System.out.println("over + " + index);
    dfs(input, index+1, next, weight-input[index][0], value+input[index][1]);
    dfs(input, next, next, weight, value);
  }
}
