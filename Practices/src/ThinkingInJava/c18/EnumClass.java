import java.lang.reflect.*;
enum Shrubbery{ GROUND, CRAWLING, HANGING }

public class EnumClass{
  public static void main(String[] args){
    for(Shrubbery s : Shrubbery.values()){
      System.out.println(s + " ordinal: " + s.ordinal());
      System.out.println(s.compareTo(Shrubbery.CRAWLING) + " ");
      System.out.println(s.equals(Shrubbery.CRAWLING) + "");
      System.out.println(s == Shrubbery.CRAWLING);
      System.out.println(s.getDeclaringClass());
      System.out.println(s.name());
      System.out.println(s);
    }
    Shrubbery s2 = Shrubbery.GROUND;
    s2 = Enum.valueOf(Shrubbery.class, "CRAWLING");
    for(Method m :s2.getClass().getMethods())
      System.out.println(m);

    for(String s : "HANGING CRAWLING GROUND".split(" ")){
      Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
      System.out.println(shrub);
    }
  }
}
