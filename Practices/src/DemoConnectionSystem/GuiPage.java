import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class GuiPage extends NamedData{
  public static final long serialVersionUID = 1l;
  public JPanel panel;
  public ArrayList<GuiPageComponent> components;

  public GuiPage(String name){
    this.name = name;
    components = new ArrayList<>();
    panel = new JPanel();
    panel.setLayout(new GridLayout(3,2,30,20));
    panel.setSize(1000,700);


  }
  public void addComponent(GuiPageComponent gpc){
    components.add(gpc);
  }

  public void paint(){
    panel.setBackground(Color.gray);
    for(GuiPageComponent gpc: components){
      panel.add(gpc.panel);
    }
  }

  public void repaint(){
    //only do repaint no calculate!
    //calculate in emmm.... main system in a new thread!!!!
    //to reflesh all the component panel;
    for(GuiPageComponent gpc: components){
      gpc.panel.repaint();
    }
  }
  public void reflesh(){
    //used in kaihatsu to show update page;
    panel.removeAll();
    this.paint();
    panel.repaint();
    this.repaint();
    panel.revalidate();
  }
}
