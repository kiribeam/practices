import javax.swing.*;
import java.awt.*;
public class Panel1 {
  public static void main(String[] args) {
    Panel1 gui = new Panel1();
    gui.go();
  }

  public void go() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setBackground(Color.darkGray);
    JButton button1 = new JButton("shock me");
    panel.add(button1);
    JButton button2 = new JButton("shock me2");
    panel.add(button2);
    JButton button3 = new JButton("shock me3");
    panel.add(button3);

    frame.getContentPane().add(BorderLayout.EAST, panel);
    frame.setSize(200, 200);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
}
