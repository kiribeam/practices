import java.util.*;

public class HWadd{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String s1 = sc.next();
    String s2 = sc.next();
    System.out.println(toMix(toInt(s1) + toInt(s2)));
  }
  public static int toInt(String s){
    int length = s.length();
    int result = 0;
    char c;
    int fac = 1;
    for(int i=0; i<length; i++){
      c = s.charAt(length-1-i);
      result += fac * ((int) c - 'a');
      fac *= 26;
    }
    System.out.println(result + "");
    return result;
  }
  public static String toMix(int i){
    String result = "";
    int cur;
    char c;
    while(i/26 != 0){
      cur = i%26;
      c = (char) ( 'a' + cur );
      result =  c + result;
      i /= 26;
    }
    result  = (char)('a' + i) + result;
    return result;
  }
}
