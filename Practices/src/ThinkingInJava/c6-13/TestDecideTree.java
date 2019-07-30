import java.util.*;

public class TestDecideTree{
  public static void main(String[] args){
    int[] current = {5, 4, 0};
    FingerStatus fs = new FingerStatus(current);
    DecideTree dt = new DecideTree( fs );
    System.out.println("Now result is :");
    if(dt.getResult()>0)
      System.out.println("First player wins!");
    else if(dt.getResult()<0)
      System.out.println("Second player wins!");
    else 
      System.out.println("ERR");
  }
}
