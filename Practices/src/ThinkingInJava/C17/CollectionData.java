import java.util.*;

public class CollectionData<T> extends ArrayList<T>{
  public CollectionData(Generator<T> gen, int kazu){
    for(int i=0; i<kazu; i++)
      add(gen.next());
  }

  public static <T> CollectionData<T> list(Generator<T> gen, int kazu){
    return new CollectionData<T>(gen, kazu);
  }
}
