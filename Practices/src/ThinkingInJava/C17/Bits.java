import java.util.*;

public class Bits{
  public static void main(String [] ars){
    BitSet b125 = new BitSet(61);
    System.out.println("size : " + b125.size());
    System.out.println("length : " + b125.length());
    b125.set(122);
    System.out.println("size : " + b125.size());
    System.out.println("length : " + b125.length());
  }
}
