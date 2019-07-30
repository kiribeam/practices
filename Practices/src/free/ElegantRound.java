import java.util.*;
public class ElegantRound{
    public static void main(String[] solve){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        sc.close();
        System.out.println(solve(n));
    }
    public static int solve(long n){
        long fc = 0;
        long front = 0;
        long bc = (long)Math.sqrt(n);
        long behind = bc*bc;
        int result = 0;
        int count = 0;
        if(bc*bc == n) count-=4;
        long half = (long)Math.sqrt(n/2);
        if(2*half*half == n) count+=4;
        while(bc>fc){
            if(front + behind == n){
                result++;
                fc++;
                bc--;
                front = fc*fc;
                behind = bc*bc;
            }else if(front+behind>n){
                bc--;
                behind = bc*bc;
            }else{
                fc++;
                front = fc*fc;
            }
        }
        return result*8+count;
    }
}
