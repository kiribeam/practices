public class Duck{
  private int size;
  public Duck(int duckSize) {
    System.out.println("Quack");

    size = duckSize;

    System.out.println("size is " + size);
  }

  public void setSize(int newsize) {
    size = newsize;
  }

  public int getSize() {
    return size;
  }
}
