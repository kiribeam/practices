import java.util.*;
public class Sekiban{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.close();
        System.out.println(solve(n,m));
    }
    public static int solve(int n, int m){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(m);
        HashSet<Integer> set = new HashSet<>();
        set.add(m);
        int result = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int head = queue.poll();
                if(set.contains(head)) continue;
                if(head<n) continue;
                if(head == n) return result;
                set.add(head);
                for(int j=head/2+1; j<head; j++){
                  if(head%(head-j)==0){
                       queue.add(j);
                       System.out.println(j);
                  } 
                }
            }
            result++;
        }
        return -1;
    }
}
