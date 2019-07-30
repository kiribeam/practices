import java.util.Scanner;
public class Aisong{
    public static void solve(){
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        int[] power = new int[n];
        for(int i=0; i<n; i++){
            power[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        int d = sc.nextInt();
        sc.close();
        long[][] dp = new long[n][k];
        for(int i=0; i<n; i++){
            dp[i][0] = power[i];
        }
        for(int i=n-1; i>=0; i--){
            for(int j=1; j<k; j++){
                if(i+j>=n) dp[i][j]=0;
                long max = 0;
                for(int l=i+1; l<n&&l<=i+d; l++){
                    max = Math.max(max, dp[l][j-1]);
                }
                dp[i][j] = max * power[i];
            }
        }
        System.out.println(dp[0][k-1]);
    }
    public static void main(String[] args){
      solve();
    }
}
