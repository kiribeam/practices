import java.util.*;
public class Wash{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int team = sc.nextInt();
        int [][] input = new int[team][];
        for(int i=0; i<team; i++){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] array = new int[2*n+2];
            array[0] = n;
            array[1] = k;
            for(int j=2; j<2*n+2; j++){
                array[j] = sc.nextInt();
            }
            input[i] = array;
        }
        sc.close();
        System.out.println(solve(input));
    }
    public static String solve(int[][] input){
        StringBuilder sb = new StringBuilder("");
        for(int[] nums : input){
            sb.append(go(nums));
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("\\");
        return sb.toString();
    }
    public static String go(int[] array){
        int n = array[0];
        int k = array[1];
        int count = 1;
        int test = 1;
        for(;count < k+1; count++){
            test = (2*test)%(2*n-1);
            if(test == 1) break;
        }
        if(test == 1) k=k%count;
        int[] now = new int[2*n];
        for(int i=0; i<2*n; i++){
            now[i] = i;
        }
        for(int i=1; i<2*n-1; i++){
            for(int j=0; j<k; j++){
                now[i] = (now[i]*2)%(2*n -1);
            }
        }
        int[] result = new int[2*n];
        for(int i=0;i<2*n; i++){
            result[now[i]] = array[i+2];
        }
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i<2*n; i++){
            sb.append(result[i]+" ");
        }
        return sb.toString();
    }
}
