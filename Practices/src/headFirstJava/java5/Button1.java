import javax.swing.*;
import java.awt.*;

public class Button1 {
  public static void main(String[] args) {
    Button1 button = new Button1();
    button.go();
  }
  void go() {
    JFrame frame = new JFrame();
    JButton button = new JButton("click me quickly!!");
    frame.getContentPane().add(BorderLayout.NORTH, button);
    frame.setSize(222, 222);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Font bigFont = new Font("serif", Font.BOLD, 28);
    button.setFont(bigFont);
  }
}
