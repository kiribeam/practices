import java.lang.reflect.*;
import java.util.*;

public class TestGetClass{
  public static void main(String[] a){
    Object obj = new TestGetClass();
    System.out.println(Arrays.toString(obj.getClass().getClasses()));
  }
  private class BB{}
  public class CC{
    Double d = 1.0;
  }
  Integer i = 1;
  public static class KK{}

}
