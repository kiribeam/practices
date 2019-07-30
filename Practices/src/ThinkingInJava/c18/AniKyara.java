import java.util.*;

interface Generator<T>{
  public T next();
}


enum AniKyara implements Generator<AniKyara>{
  AA, BB, CC, DD, EE, FFF;
  private Random rand = new Random(47);
  @Override
  public AniKyara next(){
    return values()[rand.nextInt(values().length)];
  }

  public static <T> void printNext(Generator<T> rg){
    System.out.print(rg.next() + ", ");
  }

  public static void main(String[] args){
    AniKyara ak = AniKyara.DD;
    for(int i=0; i<10; i++)
      printNext(ak);
  }
}
