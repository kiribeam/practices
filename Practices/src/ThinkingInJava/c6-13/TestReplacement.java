import java.util.regex.*;

public class TestReplacement{
  public static void main(String[] ar){
    String s = "dog is good black dog, not white dog";
    StringBuffer sb = new StringBuffer();
    Pattern p = Pattern.compile("dog");
   Matcher m = p.matcher(s);
    while(m.find()){
      m.appendReplacement(sb, "[" + m.group() + "]");
      System.out.println(sb);
    }
    m.appendTail(sb);
    System.out.println(sb);
  }
}
