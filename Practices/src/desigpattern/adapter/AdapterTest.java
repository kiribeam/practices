package desigpattern.adapter;

import java.util.ArrayList;
public class AdapterTest{
  public static void main(String[] args){
    Gundam g1 = new GP02();
    Ball b = new Ball();
    ArrayList<Gundam> gs = new ArrayList<>();
    gs.add(g1);
    gs.add(new BallAdapter(b));
    for(Gundam g: gs){
      g.say();
      g.fight();
    }
  }
}
