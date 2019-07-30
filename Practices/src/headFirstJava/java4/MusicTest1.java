import javax.sound.midi.*;

public class MusicTest1 {
  public int play() {
    try{
    Sequencer sequencer = MidiSystem.getSequencer();
    System.out.println("We got a sequencer");
    System.out.println("The a");
    return 0;
    } catch(Exception ex) {
      System.out.println("Bummer");
      System.out.println("Fuck");
      return 0;
    } finally {
      System.out.println("used finally");
    }
  }

  public static void main(String[] args) {
    MusicTest1 mt = new MusicTest1();
    mt.play();
  }
}

