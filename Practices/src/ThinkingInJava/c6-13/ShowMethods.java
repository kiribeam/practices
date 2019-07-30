import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Pattern;

public class ShowMethods{
	private static String usage = 
			"usage:\n" + "ShowMethods qu....";
	private static Pattern p = Pattern.compile("\\w+\\.");
	public static void main(String [] args){
		if(args.length < 1){
			System.out.println(usage);
			System.exit(0);
		}
		int lines = 0;
		try{
			Class<?> c = Class.forName(args[0]);
			Method[] methods = c.getMethods();
			Constructor[] ctors = c.getConstructors();
			if(args.length == 1){
				for(Method method : methods){
					System.out.println(method.toString().replaceAll("\\w+\\.", ""));
				}
				for(Constructor ctor : ctors){
					System.out.println(ctor.toString().replaceAll("\\w+\\.", ""));
				}
				lines = methods.length + ctors.length;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
  private void show(){
    ;
  }
}
