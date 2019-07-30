import java.util.*;

interface Processor<T, E extends Exception>{
  void process(List<T> resultCollector) throws E;
}

class ProcessRunner<T, E extends Exception> extends ArrayList<Processor<T, E>>{
  List<T> processAll() throws E{
    List<T> resultCollector = new ArrayList<T>();
    for(Processor<T, E> processor: this)
      processor.process(resultCollector);
    return resultCollector;
  }
}

class Failure1 extends Exception{}

class Processor1 implements Processor<String, Failure1>{
  static int count = 3;
  public void process(List<String> resultCollector) throws Failure1{
    if(count -- > 1)
      resultCollector.add("Hep!");
    else
      resultCollector.add("Ho!");
    if(count < 0) throw new Failure1();
  }
}

public class ThrowGenericException{
  public static void main(String[] args){
    ProcessRunner<String, Failure1> runner = new ProcessRunner<String, Failure1>();
    for(int i=0; i<3; i++){
      runner.add(new Processor1());
    }
    try{
      System.out.println(runner.processAll());
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
