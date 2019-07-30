//This is a source file to give a sample view for search the exit
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Gui implements View{
  private JFrame frame;
  private MyPanel panel;
  private Map map;
  private boolean flag = false;
  //to show path only after got a result
  private int[][] path;
  //the result path which will showed in gray color
  public Gui(Map m){
    map = m;
    //read map
  }
  void setGui(){
    frame = new JFrame();
    panel = new MyPanel();
    frame.getContentPane().add(BorderLayout.CENTER, panel);
    panel.setBackground(Color.white);
    frame.setSize(900,600);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public class MyPanel extends JPanel{
    public void paintComponent(Graphics g){
      Graphics g2 = (Graphics2D) g;
      //follow the order of array's order
      int x = map.height;
      int y = map.width;
      //draw background color
      g2.setColor(Color.blue);
      g2.fillRect(50, 50, y*50, x*50);
      g2.setColor(Color.white);
      for(int i=0; i<x; i++){
        for(int j=0; j<y; j++){
          if(map.canMove(i,j)) g2.fillRect(50 + 50*j, 50 + 50*i, 50,50);
          //if map data is 0, it will be a white place implies can be passed
        }
      }
      //draw the start point and goal
      int[][] sg = map.getSG();
      g2.setColor(Color.orange);
      g2.fillRect(50+ 50*sg[0][1], 50 + 50*sg[0][0],50,50);
      g2.setColor(Color.green);
      g2.fillRect(50+ 50*sg[1][1], 50 + 50*sg[1][0],50,50);
      //draw rims
      g2.setColor(Color.black);
      g2.drawLine(50,50,50,50 * x + 50);
      g2.drawLine(50,50,50+ 50*y, 50);
      g2.drawLine(50,50 + x*50,50 + 50*y, 50 + x*50);
      g2.drawLine(50 + 50*y, 50, 50 + 50 * y, + 50+ 50*x);
      //draw path
      if(flag) {
        int len = path.length;
        g2.setColor(Color.gray);
        for(int i=0;i<len-1;i++){
          g2.fillRect(50+path[i][1]*50, 50 + path[i][0]*50,50,50);
        }
      }
    }
  }
  //if get a good path, draw on the panel
  public void update(int[][] pathes){
    flag = true;
    path = pathes;
    panel.repaint();
  }
}
