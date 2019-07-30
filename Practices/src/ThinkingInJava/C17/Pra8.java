//XXXXXXXXXX
import java.util.*;


class SList<T> implements Iterable<T>{
  Link head = new Link(null);
  Link current = head;
  Link pre = null;
  class Link{
    T obj;
    Link next = null;
    Link(T obj){
      this.obj = obj;
    }
    void setNext(T obj){
      next = new Link(obj);
    }
  }
  class SListIterator implements Iterator{
    @Override
    public boolean hasNext(){
      return !current.next.equals(null);
    }
    @Override
    public T next(){
      pre = current;
      current = current.next;
      return current.obj;
    }
    @Override
    public void remove(){
      if(pre == null) return;
      pre.next = current.next;
      current = pre;
    }
    public void add(T obj){
      Link temp = new Link(obj);
      temp.next = current.next;
      current.next = temp;
      pre = current;
      current = temp;
    }
  }
  @Override
  public SListIterator iterator(){
    return new SListIterator();
  }
}

public class Pra8{
  public static void main(String[] a){
    SList<Integer> sl = new SList<>();
    SList<Integer>.SListIterator iter = sl.iterator();
    iter.add(10);
    iter.add(11);


  }
}
