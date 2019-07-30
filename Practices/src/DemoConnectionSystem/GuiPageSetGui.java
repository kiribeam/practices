import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

public class GuiPageSetGui implements ActionListener{
  private JFrame frame = new JFrame("Gui Page Karuku Tsukurou!");
  private JPanel guiMakePanel;
  private ComponentChoosePanel componentChoosePanel;
  private ProjectData projectData;
  private GuiPage guiPage;
  private SeiteiPanel seiteiPanel;
  private JTextField nameText;
  private JButton addMessageShowButton;
  private JButton addDeviceControlButton;
  //private JButton refleshButton;
  private JButton saveButton;
  private JButton deleteButton;
  public GuiPageSetGui(SeiteiPanel sp, ProjectData pd, GuiPage gp){
    seiteiPanel = sp;
    projectData = pd;
    guiPage = gp;
  }
  public void setGui(){
    if(guiPage == null) {
      guiPage = new GuiPage("New Gui Page");
    }
    guiMakePanel = new JPanel();
    guiMakePanel.setSize(200, 700);
    addMessageShowButton = new JButton("Add Message Box");
    addDeviceControlButton = new JButton("Add Device Box");
    //refleshButton = new JButton("Reflesh Gui Page");
    saveButton = new JButton("Save Gui Page");
    deleteButton = new JButton("Delete Gui Page");

    addMessageShowButton.addActionListener(this);
    addDeviceControlButton.addActionListener(this);
    saveButton.addActionListener(this);
    deleteButton.addActionListener(this);

    guiMakePanel.setLayout(new BoxLayout(guiMakePanel, BoxLayout.Y_AXIS));

    guiMakePanel.add(new JLabel("Change Component"));
    componentChoosePanel = new ComponentChoosePanel();
    componentChoosePanel.reflesh();
    guiMakePanel.add(componentChoosePanel);

    guiMakePanel.add(new JLabel("Gui Page Name:"));
    nameText = new JTextField(guiPage.name, 20);
    guiMakePanel.add(nameText);

    guiMakePanel.add(addMessageShowButton);
    guiMakePanel.add(addDeviceControlButton);
    guiMakePanel.add(saveButton);
    guiMakePanel.add(deleteButton);

    frame.getContentPane().add(BorderLayout.EAST, guiMakePanel);
    frame.getContentPane().add(BorderLayout.CENTER, guiPage.panel);
    frame.setSize(1200,700);
    frame.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent ae){
    JButton tempButton = (JButton) ae.getSource();
    String buttonString = tempButton.getText();
    System.out.println("From Guipage Set Gui buttonString is :" + buttonString);
    int n = guiPage.components.size();
    if(buttonString.equals("Add Message Box")){
      guiPage.addComponent(new GuiPageMessageShowComponent("" + n,guiPage));
      componentChoosePanel.buttons.add(new JButton("" + n));
      reflesh();
    }else if(buttonString.equals("Add Device Box")){
      guiPage.addComponent(new GuiPageDeviceControlComponent("" + n,guiPage, null));
      componentChoosePanel.buttons.add(new JButton("" + n));
      reflesh();
    }else if(buttonString.equals("Save Gui Page")){
      int x = JOptionPane.showConfirmDialog(null, "Save? ",
         "Save", JOptionPane.YES_NO_OPTION );
      if(1 == x) return;
      guiPage.name = nameText.getText();
      if(! projectData.guiPages.contains(guiPage)) 
        projectData.guiPages.add(guiPage);
      projectData.updateGuiPage();
      seiteiPanel.reflesh();
      frame.dispose();
    }else if(buttonString.equals("Delete Gui Page")){
        ;//set message
      int x = JOptionPane.showConfirmDialog(null, "Delete? ",
         "Delete", JOptionPane.YES_NO_OPTION );
      if(1 == x) return;
      guiPage.name = "Null";
      projectData.updateGuiPage();
      seiteiPanel.reflesh();
      frame.dispose();
    }else{
      System.out.println("Unknown button String in GuiPageSetGui");
      return;
    }
  }
  public void reflesh(){
    guiPage.reflesh();
    componentChoosePanel.reflesh();
  }
  class ComponentChoosePanel extends JPanel implements ActionListener{
    public static final long serialVersionUID = 1l;
    public ArrayList<JButton> buttons;
    public ComponentChoosePanel(){
      buttons = new ArrayList<>();
      setBackground(Color.pink);
      setLayout(new GridLayout(3,2,6,4));
      setSize(200,140);
      setMinimumSize(new Dimension(200,140));
      paint();
    }
    public void paint(){
      buttons = new ArrayList<>();
      int count = guiPage.components.size();
      for(int i=0; i<count; i++){
        JButton jb = new JButton("" + i);
        buttons.add(jb);
        jb.removeActionListener(this);
        jb.addActionListener(this);
        add(jb);
      }
    }
    public void reflesh(){
      removeAll();
      paint();
      repaint();
      revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent ae){
      JButton tempButton = (JButton) ae.getSource();
      String buttonString = tempButton.getText();
      int index = Integer.parseInt(buttonString);
      GuiPageComponent gpc = guiPage.components.get(index);
      if(gpc.type == 0) {
        new DeviceComponentSetGui(GuiPageSetGui.this, (GuiPageDeviceControlComponent) gpc, projectData, guiPage).setGui();
      } else if(gpc.type == 1){
        new MessageComponentSetGui(GuiPageSetGui.this, gpc, projectData, guiPage).setGui();
      } else{
        ;
      }
    }
  }

}
