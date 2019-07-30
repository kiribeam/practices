public class Jfring{
  public static int run(int m, int n){
    int result=0;
    for(int i=2; i<n+1; i++){
      result=(result+m)%i;
    }
    return result;
  }
  public static void main(String[] args){
    int m = Integer.parseInt(args[0]);
    int n = Integer.parseInt(args[1]);
    System.out.println("Now by is: " + m +":" + n + " " + (run(m, n)+1));
  }
}
