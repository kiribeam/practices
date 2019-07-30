import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class GuiPageMessageShowComponent extends GuiPageComponent{
  public static final long serialVersionUID = 1l;
  public double lowBound, highBound;
  public SystemVar systemVar;
  public GuiPageMessageShowComponent(String name, GuiPage gp){
    super(name, gp, 1);
    panel = new MessageShowPanel();
    lowBound = -99999;
    highBound = 99999;
  }
  public void updateResult(String s){
    result = s;
  }
  class MessageShowPanel extends JPanel{
    public static final long serialVersionUID = 1l;
    public MessageShowPanel(){
      setSize(300,300);
    }
    @Override
    public void paintComponent(Graphics g){
      g.setColor(Color.blue);
      g.fillRect(0,0,300,300);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
      g.setColor(Color.black);
      g.drawString("Name: " + name,15,25);
     //low/high will be down in  save part!
      try{
        double value = Double.parseDouble(systemVar.value);
        g.drawString("Low  bound is: " + lowBound, 10, 50 );
        g.drawString("High bound is: " + highBound, 10, 90 );
        if(value < lowBound || value > highBound)
          g.setColor(Color.red);
      }catch(Exception e){
        ;
      }
      if (systemVar == null) {
        g.drawString("No Var selected", 10, 150);
        return;
      }
      g.drawString("" + systemVar.name + " : " + systemVar.value,10,150);
    }
  }
}
