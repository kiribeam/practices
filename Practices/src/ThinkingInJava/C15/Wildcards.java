public class Wildcards{
  static void rawArgs(Holder holder, Object arg){
    holder.set(arg);
    Object obj = holder.get();
  }
  static <T> T exact1(Holder<T> holder){
    T t = holder.get();
    return t;
  }
  static <T> T wildSubtype(Holder<? extends T> holder, T arg){
    T t = holder.get();
    return t;
  }
  public static void main(String [] args){
    Holder raw = new Holder<Long>();
    long lng = 1l;
    rawArgs(raw , lng);
    exact1(raw);

    Holder<?> unbounded = new Holder<Long>();


    Long r9 = wildSubtype(raw, lng);
    Object r11 = wildSubtype(unbounded, lng);
  }
}
