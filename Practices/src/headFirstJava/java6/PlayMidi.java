import java.io.*;
import javax.sound.midi.*;

public class PlayMidi {
  private static File sound;
  private static Sequence seq;
  private static Sequencer midi;

  public static void play(String filename){
    try {
      sound = new File(filename);
      seq = MidiSystem.getSequence(sound);
      midi = MidiSystem.getSequencer();
      midi.open();
      midi.setSequence(seq);

      if(!midi.isRunning()) {
        midi.start();
      }
    } catch(Exception e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    PlayMidi.play("c:\\code\\java\\FirstGameK\\yorunohimawari.mid");
    long time = midi.getMicrosecondLength()/1000;

    try{
      Thread.sleep(time);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    PlayMidi.stop();

  }

  public static void stop() {
    if(midi.isRunning()) midi.stop();
    if(midi.isOpen()) midi.close();
  }
}
