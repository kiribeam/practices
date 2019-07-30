import java.util.*;

public class SimpleHashMap2<K, V> extends AbstractMap<K, V>{
  static final int SIZE = 977;
  MapEntry2[] buckets = new MapEntry2[SIZE];

  public V put(K key, V value){
    V oldValue = null;
    int index = Math.abs(key.hashCode()) % SIZE;
    MapEntry2<K,V> me = buckets[index];
    while(me != null){
      if(me.getKey().equals(key)){
        oldValue = me.getValue();
        me.setValue(value);
        return oldValue;
      }
      me = me.next;
    }
    MapEntry2<K,V> entry = new MapEntry2<>(key, value);
    entry.next = buckets[index];
    buckets[index] = entry;
    return oldValue;
  }

  public V get(Object key){
    int index = Math.abs(key.hashCode())%SIZE;
    MapEntry2<K, V> me = buckets[index];
    while(me != null){
      if(me.getKey().equals(key)){
        return me.getValue();
      }
      me = me.next;
    }
    return null;
  }
  public Set<Map.Entry<K, V>> entrySet(){
    Set<Map.Entry<K,V>> set = new HashSet<Map.Entry<K,V>>();
    for(MapEntry2<K,V> me : buckets){
      while(me != null){
        set.add(me);
        me = me.next;
      }
    }
    return set;
  }
  public static void main(String[] a){
    SimpleHashMap2<String, String> m = new SimpleHashMap2<>();
    m.putAll(Contries.capitals(4));
    System.out.println(m);
    System.out.println(m.get("CC"));
    System.out.println(m.entrySet());
  }
}
