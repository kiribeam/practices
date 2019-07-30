import java.util.*;

public class MapEntry2<K, V> implements Map.Entry<K, V>{
  private K key;
  private V value;
  MapEntry2<K, V> next = null;
  public MapEntry2(K key, V value){
    this.key = key;
    this.value = value;
  }

  public K getKey(){
    return key;
  }

  public V getValue(){
    return value;
  }
  public V setValue(V v){
    V result = value;
    value = v;
    return result;
  }
  @Override
  public int hashCode(){
    return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0:value.hashCode());
  }
  @Override
  public boolean equals(Object o){
    if(!(o instanceof MapEntry)) return false;
    MapEntry me = (MapEntry) o;
    return (key == null ? me.getKey() == null: key.equals(me.getKey())) &&
      (value == null? me.getValue() == null: value.equals(me.getValue()));
  }
  @Override
  public String toString(){
    return key + "=" + value;
  }
}
