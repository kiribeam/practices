import java.util.regex.*;
public class Groups{
  static public final String POEM =
    "aaa bbb ccc.\n ddd eee fff.";
  public static void main(String[] ar){
    Matcher m = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$")
      .matcher(POEM);
    while(m.find()){
      for(int j=0; j<=m.groupCount();j++){
        System.out.println("[" + m.group(j) + "]");
      }
    }
  }
}
