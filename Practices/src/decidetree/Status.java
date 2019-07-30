package decidetree;

import java.util.ArrayList;
public interface Status{
  public boolean over();
  public ArrayList<Status> operate();
}

class FingerStatus implements Status{
  public final int[] hands;
  public FingerStatus(int[] tmp){
    hands = tmp;
  }
  public boolean over(){
    if(hands[0] == 0 || hands[1] == 0 ||
        (hands[0] == 1 && hands[1] == 1))
      return true;
    else return false;
  }
  private int[] step(int n){
    int[] newhands = new int[3];
    if(n == 0){
      newhands[0] = hands[0] -1;
      newhands[1] = hands[1] -1;
      newhands[2] = hands[2];
    } else {
      if(hands[2] == 0){
        newhands[0] = hands[0] -1;
        newhands[1] = hands[1];
        newhands[2] = 1;
      }
      else{
        newhands[0] = hands[0];
        newhands[1] = hands[1] -1;
        newhands[2] = 0;
      }
    }
    return newhands;
  }
  public ArrayList<Status> operate(){
    if(over()) return null;
    ArrayList<Status> stats = new ArrayList<>();
    stats.add(new FingerStatus(step(0)));
    stats.add(new FingerStatus(step(1)));
    return stats;
  }
}
