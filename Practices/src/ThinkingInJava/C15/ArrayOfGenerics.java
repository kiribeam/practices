import java.util.*;
public class ArrayOfGenerics{
  public static void main(String[] a){
    List<String>[] ls;
    List[] la = new List[10];
    ls = (List<String>[]) la;
    ls[0] = new ArrayList<String>();
    //ls[1] = new ArrayList<Integer>();

    Object[] objects = ls;
    objects[1] = new ArrayList<Integer>();

    List<BB>[] bbs = (List<BB>[]) new List[10];
    for(int i=0; i<bbs.length; i++){
      bbs[i] = new ArrayList<BB>();
    }
  }
  class BB{}
}
