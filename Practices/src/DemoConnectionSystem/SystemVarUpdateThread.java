import java.util.*;
import java.io.*;
import javax.script.*;

public class SystemVarUpdateThread implements Runnable{
  public ArrayList<Var> systemVars;
  public SystemDevice systemDevice;
  public int time;
  public SystemVarUpdateThread(SystemDevice sd){
    systemDevice = sd;
    time = sd.time;
    systemVars = sd.varList;
  }
  @Override
  public void run(){
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine scriptEngine = sem.getEngineByName("javascript");

    while(true){
      for(Var v: systemVars){
        try{
          String jsCode = operate((SystemVar) v);
          //System.out.println("jscode is : " + jsCode);
          if(v.amount == 0){
            v.update(jsCode);
          }else if(v.amount != 4) {
            jsCode += " + (0.001 - 0.001)";
            double result = (double) scriptEngine.eval(jsCode);
            if(v.amount == 1) v.update("" + ((int) result));
            else v.update("" + result);
            //System.out.println(result);
          }else{
            ;
          }
        }catch(Exception ex){
          v.update("Err!");
        }
      }
      try{
       // System.out.println("A round to update System Vars");
        Thread.sleep(time);
      }catch(Exception ex){
        System.out.println("Sleep Err in update system Vars");
      }
    }
  }
  public String operate(SystemVar v) throws Exception{
    String expression = v.expression;
    //System.out.println(expression);
    int i;
    while((i=expression.indexOf('$')) >= 0){
      int j = i+1;
      while(Character.isDigit(expression.charAt(j))) {
        //System.out.println("j = " + j);
        j++;
        if(j == (expression.length())) break;
      }
      String sub = expression.substring(i+1, j);
      int count = Integer.parseInt(sub);
      //System.out.println("now using $: " + count);
      //System.out.println(v.getVar(count));
      //System.out.println(v.getVar(count).getValue() + "");
      expression = expression.substring(0,i) + v.getVar(count).getValue() + expression.substring(j);
    }
    //System.out.println("Expression is : " + expression);
    return expression;
  }
}
