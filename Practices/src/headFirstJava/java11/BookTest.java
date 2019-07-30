package headFirstJava.java11;
import java.util.*;
class BookCompare implements Comparator<Book> {
  public int compare(Book one, Book two) {
    return one.title.compareTo(two.title);
  }
}
public class BookTest{
  public static void main(String[] args) {
    new BookTest().go();
  }

  void go() {
    Book b1 = new Book("How Cats work");
    Book b2 = new Book("Remix your Body");
    Book b3 = new Book("Fingding Emo");
    BookCompare bCompare = new BookCompare();
    TreeSet<Book> tree = new TreeSet<Book>(bCompare);
    tree.add(b1);
    tree.add(b2);
    tree.add(b3);
    System.out.println(tree);
  }
}

class Book {
  String title;
  Book(String s) {
    title = s;
  }
 public String toString() {
    return title;
  }
}
