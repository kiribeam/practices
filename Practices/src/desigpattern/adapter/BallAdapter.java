package desigpattern.adapter;
public class BallAdapter implements Gundam{
  private Ball ball;
  public BallAdapter(Ball b){
    ball = b;
  }
  public void say(){
    ball.say();
    System.out.println("Now I paint a Gundam's head!");
  }
  public void fight(){
    ball.fight();
    System.out.println("Now I hava psycho attack");
  }
}
