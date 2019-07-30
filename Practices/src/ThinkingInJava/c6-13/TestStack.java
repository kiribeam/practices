import java.util.ArrayList;
public class TestStack{
  public static String calculate(String s){
    int length = s.length();
    String tmp = "";
    char c = '!';
    Stack<Character> stack = new Stack<>();
    char[] chars = s.toCharArray();
    for(int i=0; i<length; i++){
      if(chars[i] == '+'){
        i++;
        stack.push(chars[i]);
      }else if(chars[i] == '-'){
        c = stack.pop();
        tmp += c;
      }else return "Err input!";
    }
    return tmp;
  }
  public static void main(String[] args){
    String s = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+-+r+u--+l+e+s---";
    System.out.println(TestStack.calculate(s));
  }
}
