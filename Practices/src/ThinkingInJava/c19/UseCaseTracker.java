import java.util.*;
import java.lang.reflect.*;

public class UseCaseTracker{
  public static void trackUseCases(List<Integer> useCases, Class<?> cl){
    for(Method m : cl.getDeclaredMethods()){
      UseCase uc = m.getAnnotation(UseCase.class);
      if(uc != null){
        System.out.println("Found Use Case" + " : " + uc.id() + " " +  uc.description());
        useCases.remove(new Integer(uc.id()));
      }
    }

    for(int i : useCases){
      System.out.println("Warning: Missing case : " + i);
    }
  }

  public static void main(String[] args){
    List<Integer> useCases = new ArrayList<>();
    Collections.addAll(useCases, 47, 48, 49 , 50);
    trackUseCases(useCases, PasswordUtils.class);
  }
}
