
import java.awt.*;
import javax.swing.*;

public class demo01 extends JFrame {
  MyPanel mp = null;
  public static void main(String[] args) {
    demo01 demo = new demo01();
  }

  public demo01(){
    mp = new MyPanel();
    this.add(mp);
    this.setSize(400,300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}

class MyPanel extends JPanel {
  public void paint(Graphics g) {
    super.paint(g);
    g.setColor(Color.orange);
    g.drawOval(100,100,30,30);
  }
}
