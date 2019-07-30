import java.util.*;
public class Hungry{
    public static final long R = 1000000007L;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long start = sc.nextLong();
        sc.close();
        System.out.println("c b" + "c".compareTo("bb"));
        System.out.println(solve(start));
    }
    public static long solve(long start){
        start = 4*start + 3;
        start %= R;
        if(start == 0) return 1;
        int count = 3;
        for(int i=0; i<300000-2; i++, count++){
            start = start << 1;
            start ++;
            System.out.println("start at " + count + " : " + start);
            start %= R;
            if(start == 0) break;
        }
        if(start == 0) return (count+2)/3;
        return -1;
    }
}
