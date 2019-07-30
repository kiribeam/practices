import java.util.*;
public class EspDug{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        char[][] map = new char[n][m];
        for(int i=0; i<n; i++){
            String line = sc.nextLine();
            for(int j=0; j<m; j++){
                map[i][j] = line.charAt(j);
            }
        }
        int sx = sc.nextInt();
        int sy = sc.nextInt();
        int k = sc.nextInt();
        int[][] step = new int[k][2];
        for(int i=0; i<k; i++){
            for(int j=0; j<2; j++){
                step[i][j] = sc.nextInt();
            }
        }
        sc.close();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(sx*m+sy);
        int count = -1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int tmp = queue.poll();
                int x = tmp/m;
                int y = tmp%m;
                map[x][y] = 'O';
                for(int[] one : step){
                    int x2 = x+one[0];
                    int y2 = y+one[1];
                    int tmp2 = x2*m + y2;
                    //System.out.println("tmp2 : " + tmp2);
                    if(x2<0 || y2<0 || x2>=n || y2>=m || map[x2][y2]=='O' || map[x2][y2]=='X')
                      continue;
                    queue.add(tmp2);
                }
            }
         for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(map[i][j]);
                if(map[i][j] == '.') count=-1;
            }
            System.out.println("");
        }           
        System.out.println("count : " + count);
          count++;
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(map[i][j]);
                if(map[i][j] == '.') count=-1;
            }
            System.out.println("");
        }
        System.out.println(count);
    }
}
