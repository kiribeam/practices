import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public abstract class ComponentSetGui implements ActionListener{
  public GuiPageComponent component;
  public GuiPageSetGui guiPageSetGui;
  public GuiPage guiPage;
  public JFrame frame;
  public JPanel panel;
  public ProjectData projectData;
  public JTextField nameText;
  public JList<String> list;

  public ComponentSetGui(GuiPageSetGui g, GuiPageComponent c, ProjectData pd ,GuiPage gp){
    guiPageSetGui = g;
    component = c;
    projectData = pd;
    guiPage = gp;
    JLabel nameLabel = new JLabel("Name:");
    nameText = new JTextField(component.name, 30);
    frame = new JFrame("Set component");
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

  }

  public void setGui(){
    ;
  }
  public void actionPerformed(ActionEvent ae){
    ;
  }

}
