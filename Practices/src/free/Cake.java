import java.util.*;
public class Cake{
    private static long result;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        sc.close();
        solve(w, h);
        System.out.println(result);
    }
    public static void solve(int w, int h){
        boolean[][] stat = new boolean[h][w];
        dfs(stat, 0, 0l);
    }
    public static void dfs(boolean[][] stat, int va, long num){
        int w = stat[0].length;
        int h = stat.length;
        if(va>=w*h) return;
        int x = va/w;
        int y = va%w;
        if(stat[x][y]){
          dfs(stat, va+1, num);
          return;
        } 
        num ++;
        stat[x][y] = true;
        if(num>result){
          result = num;
          System.out.println("x " + " y" + x + y);
          System.out.println(va);
        }
        if(y+2<w && !stat[x][y+2]){
            stat[x][y+2] = true;
        }
        if(x+2<h && !stat[x+2][y]){
            stat[x+2][y] = true;
        }
          /*System.out.println(num);
          for(boolean[] b: stat)
            System.out.println(Arrays.toString(b));
            */
        dfs(stat, va+1, num);
    }
}
