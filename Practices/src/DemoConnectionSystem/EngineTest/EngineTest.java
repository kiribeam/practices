import javax.script.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
public class EngineTest{
  public static void main(String [] args) throws Exception{
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine scriptEngine = sem.getEngineByName("javascript");


    String jsCode= "3+4\n";
    int i = (int) scriptEngine.eval(jsCode);
    System.out.println(i);

    String expression = "($1) + ($2) * 2 + 0.001 - 0.001";
    while((i=expression.indexOf('$')) >= 0){
      int j = i+1;
      while(Character.isDigit(expression.charAt(j))) j++;
      String sub = expression.substring(i+1, j);
      int count = Integer.parseInt(sub);
      System.out.println("now using $: " + count);
      //expression.replaceAll(expression.substring(i, j), v.getVar(count).getValue());
      System.out.println(expression.substring(i, j));
      expression = expression.substring(0,i) + "001" + expression.substring(j);
      System.out.println(expression);
      try{
        Thread.sleep(2000);
      }catch(Exception e){}
    }
    System.out.println("" + ((double) scriptEngine.eval(expression)));
  }
}
