import java.util.*;

public class Contries{
  public static final String[][] DATA = {
    {"AA", "A1"},
    {"BB", "B1"},
    {"CC", "C1"},
    {"DD", "D1"},
    {"EE", "E!"}
  };
  private static class FlyweightMap extends AbstractMap<String, String>{
    private static class Entry implements Map.Entry<String, String>{
      int index;
      Entry(int index){
        this.index = index;
      }
      @Override
      public boolean equals(Object o){
        System.out.println("judge equals : " + DATA[index][0]);
        return DATA[index][0].equals(o);
      }
      public String getKey(){
        System.out.println("get Key : " + DATA[index][0]);
        return DATA[index][0];
      }
      public String getValue(){
        System.out.println("get Value :" + DATA[index][1]);
        return DATA[index][1];
      }
      public String setValue(String value){
        throw new UnsupportedOperationException();
      }
      public int hashCode(){
        System.out.println("hashCode run");
        return DATA[index][0].hashCode();
      }
    }

    static class EntrySet extends AbstractSet<Map.Entry<String, String>>{
      private int size;
      EntrySet(int size){
        if(size<0) this.size=0;
        else if(size>DATA.length)
          this.size = DATA.length;
        else this.size = size;
      }
      public int size(){
        return size;
      }
      private class Iter implements Iterator<Map.Entry<String, String>>{
        private Entry entry = new Entry(-1);
        public boolean hasNext(){
          System.out.println("has next entry.index = " + entry.index);
          return entry.index < size - 1;
        }
        public Map.Entry<String, String> next(){
          System.out.println("Index ++ : " +entry.index);
          entry.index++;
          return entry;
        }
        public void remove(){
          throw new UnsupportedOperationException();
        }
      }
      public Iterator<Map.Entry<String, String>> iterator(){
        System.out.println("Get iterator");
        return new Iter();
      }
    }
    private static Set<Map.Entry<String, String>> entries;
    static{
      entries = new EntrySet(DATA.length);
      System.out.println("Set entries");
    }
    public Set<Map.Entry<String, String>> entrySet(){
      System.out.println("get entries set");
      return entries;
    }
  }
  static Map<String, String> select(final int size){
    return new FlyweightMap(){
      public Set<Map.Entry<String, String>> entrySet(){
        return new EntrySet(size);
      }
    };
  }

  static Map<String, String> map;
  static {
    map = new FlyweightMap();
    System.out.println("Init FlyweightMap");
  }
  public static Map<String, String> capitals(){
    return map;
  }
  public static Map<String, String> capitals(int size){
    return select(size);
  }
  static List<String> names;
  static {
    names = new ArrayList<String>(map.keySet());
    System.out.println("names key set init");
  }
  public static List<String> names(){
    return names;
  }
  public static List<String> names(int size){
    return new ArrayList<String>(select(size).keySet());
  }
  public static void main(String[] args){
//    System.out.println(capitals(4));
//    System.out.println(names(4));
//    System.out.println(new HashMap<String, String>(capitals(3)));
    System.out.println(capitals().get("BB"));
  }
}
