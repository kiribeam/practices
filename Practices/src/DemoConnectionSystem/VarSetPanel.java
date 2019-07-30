import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

public class VarSetPanel extends JPanel implements ActionListener{
  public static final long serialVersionUID = 1l;
  private ArrayList<Var> varList;
  public ArrayList<JButton> buttons;
  private Device device;

  public VarSetPanel(Device d){
    device = d;
    this.varList = d.varList;
    paint();
  }
  @Override
  public void actionPerformed(ActionEvent e){
    JButton tempButton = (JButton) e.getSource();
    String buttonString = tempButton.getText();
    System.out.println("get button name" + buttonString);
    String varName = buttonString.split("/")[0];
    System.out.println(varName);
    Var var = null;
    if(varName.equals("Add Var")) var = new Var("new var");
    else var = (Var) NamedDataOperate.getNamedData(varList, varName);
    new VarChangeGui(this, device, var).setGui();
  }
  public void paint(){
    buttons = new ArrayList<>();
    buttons.add(new JButton("Add Var"));
    for(Var v: varList){
      JButton jb = new JButton(v.name + "/Device: " + device.name + "  Length: " + v.amount + "  ADDR: "+  v.addr);
      buttons.add(jb);
      System.out.println("get var");
    }
    for(JButton b: buttons) {
      b.addActionListener(this);
      add(b);
    }
    setLayout(new GridLayout(64,6,30,20));
    setSize(800,1600);
  }
  public void reflesh(){
    removeAll();
    paint();
    repaint();
    revalidate();
  }
}
