import java.util.*;
import java.util.regex.*;
public class StringEx10{
  public static void main(String[] ar){
    String s = "Java now has regular expressions";
    ArrayList<String> regex = new ArrayList<>();
    regex.add("^Java");
    regex.add("\\Breg.*");
    regex.add("n.w\\s+h(a|i)s");
    regex.add("s?");
    regex.add("s+");
    regex.add("s{4}");
    regex.add("s{1}");
    regex.add("s{0,3}");
    for(String tmp : regex){
      Pattern p = Pattern.compile(tmp);
      Matcher m = p.matcher(s);
      if(m.find())
        System.out.println("String " + tmp + " matches");
      else
        System.out.println("String " + tmp + " doesn't matches");
    }
    Pattern ps = Pattern.compile("Java");
    Matcher ms = ps.matcher("Java");
    if(ms.matches())
      System.out.println("ok");
    else 
      System.out.println("bu ok");
  }
}
