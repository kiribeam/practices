import java.util.*;
public class Order{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] array = new String[n];
        for(int i=0; i<n; i++){
            array[i] = sc.nextLine();
        }
        sc.close();
        System.out.println(solve(array));
    }
    public static String solve(String[] array){
        boolean lex = true;
        boolean leg = true;
        String tmp = array[0];
        String cur;
        int len = array.length;
        for(int i=1; i<len; i++){
            cur = array[i];
            if(leg && cur.length()<tmp.length()){
                leg = false;
            }
            if(lex && tmp.compareTo(cur)>0){
                lex = false;
            }
            if(!lex && !leg) break;
            tmp = cur;
        }
        if(lex&&leg) return "both";
        if(lex) return "lexicographically";
        if(leg) return "lengths";
        return "none";
    }
}
