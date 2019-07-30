import java.util.*;
public class InD3{
  static long [][] linearTable;
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt();
    int n = sc.nextInt();
    int[][] map = new int[m][n];
    linearTable = new long[m+n][m+n];
    String tmp;
    for(int i=0; i<m; i++){
      tmp = sc.next();
      for(int j=0; j<n; j++){
        if(tmp.charAt(j) == '#')
          map[i][j] = 1;
      }
    }
    int x = sc.nextInt();
    int y = sc.nextInt();
    long result = doSplit(x, y, map);
    System.out.println(result);
  }
  public static long doSplit(int x, int y , int[][] map){
    int[][][] sub = splitMap(x, y, map);
    int m = map.length;
    int n = map[0].length;
    return (subPart(x, y, sub[0]) * subPart(m-x+1, n-y+1, sub[1]));
  }
  public static long subPart(int m, int n, int[][] map){
    if(m==1 || n==1){
      if(checkMap(map)) return 1;
      else return 0;
    }
    if(checkMap(map)) return total(m, n);
    int min = m<n? m : n;
    long result = 0;
    for(int i=0; i<min; i++){
      if(map[i][min-1-i] == 1) continue;
      result += doSplit(i+1, min-i, map);
    }
    return result;
  }
  public static boolean checkMap(int[][] map){
    for(int i=0; i<map.length;i++)
      for(int j=0; j<map[0].length; j++)
        if(map[i][j] == 1) return false;
    return true;
  }
  public static int[][][] splitMap(int x, int y, int[][] map){
    int[][][] result = new int[2][][];
    int m=map.length;
    int n=map[0].length;
    result[0] = new int[x][y];
    result[1] = new int[m-x+1][n-y+1];
    for(int i=0; i<x; i++){
      for(int j=0; j<y; j++){
        result[0][i][j]=map[i][j];
      }
    }
    for(int i=0; i<m-x+1; i++){
      for(int j=0; j<n-y+1; j++){
        result[1][i][j] = map[i+x-1][j+y-1];
      }
    }
    return result;
  }
  public static long total(int m, int n){
    if(m==1) return 1;
    if(n==1) return 1;
    if(m>n){
      if(linearTable[m][n]!=0)
         return linearTable[m][n];
    }else{
      if(linearTable[n][m]!=0)
        return linearTable[n][m];
    }
    long result = (total(m-1, n) + total(m, n-1));
    if(m>n) linearTable[m][n] = result;
    else linearTable[n][m] = result;
    return result;
  }
}
