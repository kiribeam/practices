package headFirstJava.java11;
import java.util.Comparator;
import java.util.TreeSet;
public class BookCompare implements Comparator {
  public int compare(Book one, Book two) {
    return one.title.compareTo(two.title);
  }

  public static void main(String[] args) {
    new BookCompare.go();
  }

  void go() {
    Book b1 = new Book("How Cats work");
    Book b2 = new Book("Remix your Body");
    Book b3 = new Book("Fingding Emo");
    BookCompare bCompare = new BookCompare();
    TreeSet<Book> tree = new TreeSet<Book>();
    tree.add(b1);
    tree.add(b2);
    tree.add(b3);
    System.out.println(tree);
  }

@Override
public int compare(Object o1, Object o2) {
	// TODO Auto-generated method stub
	return 0;
}
}

class Book {
  String title;
  Book(String s) {
    title = s;
  }
}
