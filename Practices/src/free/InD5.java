import java.util.*;

public class InD5{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    int[] counts = new int[26];
    int length = s.length();
    for(int i=0; i<length; i++){
      counts[s.charAt(i) - 'a'] ++;
    }
    int max = 0;
    for(int i : counts){
      if(length%2 == 0 && i>length/2){
        System.out.println("-1");
        return;
      }else if(length%2 !=0 && i>(length/2+1)){
        System.out.println("-1");
        return;
      }
      max = max>i ? max : i;
    }
    StringBuilder sb= new StringBuilder("");
    String result = sort(sb, counts, -1, max);
    System.out.println(result);
  }
  public static String sort(StringBuilder s, int[] counts, int ignore, int max){
    for(int i=0; i<counts.length; i++){
      if(counts[i] != 0 && i !=ignore){
        //System.out.println("now ignore is" + ignore + " : " + s);
        s = s.append((char)('a' + i) + "");
        counts[i] --;
        return sort(s, counts, i, max);
      }
    }
    if(counts[ignore] > 0) {
      int exist = max - counts[ignore];
      while(counts[ignore] > 0){
        s.insert(s.length()-2*exist, (char) (ignore + 'a'));
        exist ++;
        counts[ignore] --;
      }
      return s.toString();
    }else return s.toString();
  }
}
