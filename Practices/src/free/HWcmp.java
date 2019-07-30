import java.util.*;

public class HWcmp{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String s1 = sc.next();
    String s2 = sc.next();
    System.out.println(cmp(s1, s2));
  }
  public static boolean cmp(String s1, String s2){
    boolean[] array = new boolean[26];
    char c;
    int length1 = s1.length();
    int tmp;
    for(int i=0; i<length1; i++){
      c = s1.charAt(i);
      tmp = ((int) c - 'A');
      array[tmp] = true;
    }
    int length2 = s2.length();
    for(int i=0; i<length2; i++){
      c = s2.charAt(i);
      tmp =( (int) c - 'A');
      if(!array[tmp]) return false;
    }
    return true;
  }
}
