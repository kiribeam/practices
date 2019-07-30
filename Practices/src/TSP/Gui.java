import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Gui{
  private JFrame frame;
  private MyPanel panel;
  private CmapTsp map;
  private Group group;
  private boolean flag;
  public Gui(Group g, CmapTsp m){
    map = m;
    group = g;
  }

  public void update(){
    flag = true;
    panel.repaint();
  }

  public void setGui(){
    frame = new JFrame("TSP ");
    panel = new MyPanel();
    frame.getContentPane().add(BorderLayout.CENTER, panel);
    panel.setBackground(Color.gray);
    frame. setSize(600, 600);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public class MyPanel extends JPanel{
    public void paintComponent(Graphics g3d){
      Graphics2D g = (Graphics2D) g3d;
      double r = 200;
      double center = 300;
      int r1 = 10;
      int pointNum = map.citiesNumber;
      g.setColor(Color.green);
      ArrayList<City> temp = map.getCities();
      int[][] position = new int[pointNum][2];
      for(int i=0;i<pointNum;i++){
        position[i][0] = (int) ((r *temp.get(i).x) + center);
        position[i][1] = (int) ((r *temp.get(i).y) + center);
        g.fillOval(position[i][0]-r1,
                   position[i][1]-r1,
                   2*r1,
                   2*r1);
      }
      if(flag) {
        int[] path = group.bestGen.getPath();
        g.setColor(Color.darkGray);
        g.setStroke(new BasicStroke(4.0f));
        for(int i=0;i<pointNum-1;i++){
          g.drawLine(position[path[i]][0],
                      position[path[i]][1],
                      position[path[i+1]][0],
                      position[path[i+1]][1]);
        }
      }
    }
  }
}
