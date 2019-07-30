class ScaryException extends Exception{;}

public class TestExceptions {
  public static void main(String[] args) {
    
    String test = "yes";
    try {
      System.out.println("Start try");
      doRisky(test);
      System.out.println(" end try");
    } catch(ScaryException se) {
      System.out.println("Scary Exception");
    } finally {
      System.out.println("finally");
    }
    System.out.println("end of main");
  }

  static void doRisky(String test) throws ScaryException {
    System.out.println("start risky");
    if("yes".equals(test)) {
      throw new ScaryException();
    }
    System.out.println("end risky");
    return;
  }
}
