public class Duck {
  private static int size = 9;
  
  public Duck() {
    size++;
  }
  public static void main( String[] args ) {
    System.out.println("Size of duck is  " + size);
    Duck d = new Duck();
    System.out.println("Size of duck d is" + d.size );
    System.out.println("size is "+  size);
  }

  public void setSize(int s) {
    size = s;
  }

  public int getSize() {
    return size;
  }
}
