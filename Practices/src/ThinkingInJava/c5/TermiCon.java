class Book{
  boolean checkedOut = false;
  Book(boolean c) {
    checkedOut = c;
  }

  void checkIn() {
    checkedOut = false;
  }

  protected void finalize() {
    if(checkedOut)
      System.out.println("Error: checked out");
  }
}

public class TermiCon{
  public static void main(String[] args) {
    Book novel = new Book(true);
    novel.checkIn();
    novel = new Book(false);
    new Book(true);
    new Book(true);
    new Book(true);
    new Book(true);
    System.gc();
  }
}
