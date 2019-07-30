public class NewVarArgs{
  static void printArray(Object...args){
    for(Object o: args){
      System.out.println(o + "");
    }
  }
  public static void main(String[] args){
    printArray("1", "2", "3");
    System.out.println("");
    for(String s : args)
      System.out.println(" args is: "  + s);
  } 
}
