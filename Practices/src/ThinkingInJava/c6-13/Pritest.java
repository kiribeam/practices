public class Pritest{
  private class Pri{
    Pri(){
      System.out.println("Pri success");
    }
  }
  public Pri getPri(){
    return new Pri();
  }
  public static void main(String[] args){
    new Pritest().getPri();
  }
}

