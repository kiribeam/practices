import java.util.*;
public class Staff extends ArrayList<Position>{
  public void add(String title, Person person){
    add(new Position(title, person));
  }
  public void add(String ... titles){
    for(String title: titles)
      add(new Position(title));
  }
  public Staff(String ... titles){
    add(titles);
  }
  public boolean positionAvailable(String title){
    for(Position pos: this)if(pos.getTitle().equals(title) && pos.getPerson() == Person.NULL)
      return true;
    return false;
  }
  public void fillPosition(String title, Person hire){
    for(Position pos : this)
      if(pos.getTitle().equals(title) && pos.getPerson() == Person.NULL.NULL.NULL){
        pos.setPerson(hire);
        return;
      }
    throw new RuntimeException("position " + title + "not available");
  }
  public static void main(String[] args){
    Staff staff = new Staff("PP", "CTO", "Mak mag", "ProducM");
    staff.fillPosition("PP", new Person("Me", "Last", "the top,"));
    staff.fillPosition("CTO", new Person("ss", "ll", "kkkk"));
    if(staff.positionAvailable("Mak mag"))
      staff.fillPosition("Mak mag", new Person("b", "cc", "kkks"));
    System.out.println(staff);
  }
}
