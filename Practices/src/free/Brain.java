import java.util.*;
import java.util.regex.*;
public class Brain{
    public static void main(String[] args){
       // solve(ll);
        System.out.println(Pattern.matches("aca.*aa", "aaacaaa"));
        System.out.println(Pattern.matches("acaa*", "aaacaa"));
    }
    public static void solve(LinkedList<String> ll){
        while(!ll.isEmpty()){
            String t = ll.poll();
            String s1 = ll.poll();
            String s2 = ll.poll();
            String pattern1 = s1 + ".*" + s2;
            String pattern2 = s2 + ".*" + s1;
            int re = Pattern.matches(pattern1, t) ? 1 : 0;
            re += (Pattern.matches(pattern2, t) ? 2 : 0);
            if(re == 3) System.out.println("both");
            else if(re == 2) System.out.println("backward");
            else if(re == 1) System.out.println("forward");
            else System.out.println("invalid");
        }
    }
}
