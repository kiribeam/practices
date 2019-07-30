import java.util.*;

public interface Robot{
  String name();
  String model();
  List<Operation> operations();
  class Test{
    public static void test(Robot r){
      if(r instanceof Null){
        System.out.println("NUll robot");
      }
      System.out.println("name " + r.name());
      System.out.println("model" + r.model());
      for(Operation operation : r.operations()){
        System.out.println(operation.description());
        operation.command();
      }
    }
  }
}
