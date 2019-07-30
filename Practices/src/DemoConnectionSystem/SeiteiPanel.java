import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

public class SeiteiPanel extends JPanel implements ActionListener{
  public static final long serialVersionUID = 1l;
  private ProjectData projectData;
  private ArrayList<Protocol> protocolList;
  public ArrayList<JButton> buttons;
  public JButton addButton;
  public SeiteiPanelActor actor;

  public SeiteiPanel(ProjectData pd, ArrayList<Protocol> pl, SeiteiPanelActor ac){
    projectData = pd;
    protocolList = pl;
    actor = ac;
    paint();
  }
  public void actionPerformed(ActionEvent e){
    JButton tempButton = (JButton) e.getSource();
    String buttonString = tempButton.getText();
    System.out.println("get button name" + buttonString);
    actor.action(this, projectData, protocolList, buttonString);
  }
  public void paint(){
    this.setLayout(new GridLayout(4,6,30,20));
    buttons = new ArrayList<>();
    addButton = new JButton("Add New");
    buttons.add(addButton);
    ArrayList<String> sl = actor.getButtonNameList(projectData);
    for(String s: sl){
      JButton jb = new JButton(s);
      buttons.add(jb);
    }
    for(JButton jb: buttons){
      this.add(jb);
      jb.addActionListener(this);
    }
  }
  public void reflesh(){
    removeAll();
    paint();
    repaint();
    revalidate();
  }
}

