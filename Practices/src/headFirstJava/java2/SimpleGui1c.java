import javax.swing.*;
import java.awt.*;
public class SimpleGui1c {
  public static void main(String[] args) {
    SimpleGui1c gui = new SimpleGui1c();
    gui.go();
  }
  public void go() {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);
    frame.setVisible(true);
    MyPaint g = new MyPaint();
    frame.add(g);

  }
}

class MyPaint extends JPanel {
  public void paintComponent(Graphics g) {
    g.fillRect(0,0,this.getWidth(), this.getHeight());
    int red = (int) (Math.random() * 255);
    int green = (int) (Math.random() * 255);
    int blue = (int) (Math.random() * 255);

    Color randomColor = new Color(red, green, blue);
    g.setColor(randomColor);
    g.fillOval(70, 70, 100, 100);
  }
}
