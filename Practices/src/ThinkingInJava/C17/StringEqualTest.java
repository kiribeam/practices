public class StringEqualTest{
  public static void main(String[] args){
    String a = "abc";
    String b = "abc";
    String c = new String("abc");
    String d = "a" + "b" + "c";
    String e = (a + "c").substring(0,3);
    StringBuilder sb = new StringBuilder();
    sb.append("abc");

    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(a == d);
    System.out.println(a == e);
    System.out.println(sb);
    System.out.println(a == sb.toString());
  }
}
