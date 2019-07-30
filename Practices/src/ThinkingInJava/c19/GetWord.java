import java.util.*;
public class GetWord{
  public static void main(String[] args){
    Class<?> c = java.lang.annotation.RetentionPolicy.class;
    System.out.println(Arrays.toString(c.getEnumConstants()));
  }
}
