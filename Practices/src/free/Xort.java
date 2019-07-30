import java.util.*;

public class Xort{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] ar = new int[n];
        for(int i=0; i<n; i++){
            ar[i] = sc.nextInt();
        }
        sc.close();
        System.out.println(solve(ar, m));
    }
    
    public static int solve(int[] ar, int m){
        int sm = 0;
        for(int j=19; j>=0; j--){
            if(((1<<j)&m)!=0){
                sm = j;
                break;
            }
        }
        System.out.println(sm);
        int[] sizes = new int[21-sm];
        ArrayList<LinkedList<Integer>>  al = new ArrayList<>(21-sm);
        for(int i=0; i<21-sm; i++){
            al.add(new LinkedList<Integer>());
        }
        for(int i=0; i<ar.length; i++){
            for(int j=19; j>=0; j--){
                if(((1<<j)&ar[i]) != 0){
                    if(j>=sm){    
                         al.get(j-sm).add(ar[i]);
                        sizes[j-sm]++;
                    }else{
                        sizes[20-sm]++;
                        al.get(20-sm).add(ar[i]);
                    }
                    break;
                }
            }
        }
       // for(LinkedList<Integer> l : al){
     //       System.out.println(l);
     //   }
        int result = 0;
        int sum = ar.length;
        for(int j=19-sm; j>sm; j--){
            result += sizes[j]*(sum-sizes[j]);
            sum -= sizes[j];
            System.out.println("j= " + j + " , result=" + result);
            for(int i=0; i<sizes[j]; j++){
                for(int k=i+1; k<sizes[j]; k++){
                    if((al.get(j).get(i)^al.get(j).get(k)) >m) result++;
                }
            }
            
        }
        System.out.println(result);
        for(int i : al.get(0)){
            for(int j : al.get(20-sm)){
                if((i^j) > m) result++;
            }
        }
        return result;
    }
}
