public class Check{
  public static void main(String[] args){
    int d1 = Integer.parseInt(args[0]);
    int d2 = Integer.parseInt(args[1]);
    System.out.println(!check(((d1&(~d2)) | ((d2&(~d1))))));
  }
  public static boolean check(int i){
    if(i==0) return false;
    return true;
  }
}
