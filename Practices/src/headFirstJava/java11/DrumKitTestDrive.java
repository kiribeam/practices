package headFirstJava.java11;
class DrumKitTestDrive {
  public static void main(String[] args) {
    DrumKit d = new DrumKit();
    boolean topHat = true;
    boolean snare = true;
    d.playSnare();
    d.playTopHat();
  }
}
