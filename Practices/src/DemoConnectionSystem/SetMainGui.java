import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

public class SetMainGui{
  private JFrame setFrame = new JFrame("Project Setting");
  private ProjectData projectData;
  private ArrayList<Protocol> protocolList = ProtocolOperate.getProtocolList();
  private JPanel buttonPanel;
  private JPanel cardPanel;
  private CardLayout cardLayout;
  private JTextField projectNameText;

  public SetMainGui(ProjectData pd){
    projectData = pd;
  }

  public void setGui(){
    if(projectData != null)
      setFrame.setTitle("Project Setting -----" + projectData.projectName);
    else projectData = new ProjectData("Undefined Project");

    buttonPanel = new ButtonPanel();
    projectNameText = new JTextField();
    projectNameText.setColumns(15);
    projectNameText.setText(projectData.projectName);
    projectNameText.addActionListener(new ProjectRenameListener());

    JButton dataBaseButton = new JButton("DataBase");
    dataBaseButton.addActionListener(new DataBaseListener());

    JButton socketDeviceButton = new JButton("SocketDevice");
    socketDeviceButton.addActionListener(new SocketDeviceListener());

    JButton otherDeviceButton = new JButton("OtherDevice");
    otherDeviceButton.addActionListener(new OtherDeviceListener());

    JButton guiPagesButton = new JButton("GuiPages");
    guiPagesButton.addActionListener(new GuiPagesListener());

    JButton systemVarButton = new JButton("SystemVar");
    systemVarButton.addActionListener(new SystemVarListener());

    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(new SaveListener());


    buttonPanel.add(new JLabel("Project Name :"));
    buttonPanel.add(projectNameText);
    buttonPanel.add(dataBaseButton);
    buttonPanel.add(socketDeviceButton);
    buttonPanel.add(otherDeviceButton);
    buttonPanel.add(guiPagesButton);
    buttonPanel.add(systemVarButton);
    buttonPanel.add(saveButton);


    cardPanel = new CardPanel();
    cardLayout = new CardLayout();
    cardPanel.setLayout(cardLayout);

    JPanel dataBasePanel = new SeiteiPanel(projectData, protocolList, new DataBaseGuiActor());
    JPanel socketDevicePanel = new SeiteiPanel(projectData, protocolList, new SocketDeviceGuiActor());
    JPanel otherDevicePanel = new SeiteiPanel(projectData, protocolList, new OtherDeviceGuiActor());
    JPanel guiPagesPanel = new SeiteiPanel(projectData, protocolList, new GuiPagesGuiActor());
    JPanel systemVarPanel = new SeiteiPanel(projectData, protocolList, new SystemVarGuiActor());


    cardPanel.add("DataBase", dataBasePanel);
    cardPanel.add("SocketDevice", socketDevicePanel);
    cardPanel.add("OtherDevice", otherDevicePanel);
    cardPanel.add("GuiPages", guiPagesPanel);
    cardPanel.add("SystemVar", systemVarPanel);

    setFrame.getContentPane().add(BorderLayout.NORTH, buttonPanel);
    setFrame.getContentPane().add(BorderLayout.CENTER, cardPanel);
    setFrame.setSize(1000,600);
    setFrame.setVisible(true);
    setFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  class CardPanel extends JPanel{
    public static final long serialVersionUID = 1l;
  }
  class ButtonPanel extends JPanel{
    public static final long serialVersionUID = 1l;
  }
  class ProjectRenameListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      String s = projectNameText.getText();
      int n = JOptionPane.showConfirmDialog(null, "Save As New Project " +s,
         "Create Project", JOptionPane.YES_NO_OPTION );
      if(1 == n) projectNameText.setText(projectData.projectName);
      else projectData.projectName = s;
    }
  }
  class DataBaseListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      cardLayout.show(cardPanel,"DataBase");
    }
  }
  class SocketDeviceListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      cardLayout.show(cardPanel,"SocketDevice");
      System.out.println("changed");
    }
  }
  class OtherDeviceListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      cardLayout.show(cardPanel,"OtherDevice");
    }
  }
  class GuiPagesListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      cardLayout.show(cardPanel,"GuiPages");
    }
  }
  class SystemVarListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      cardLayout.show(cardPanel,"SystemVar");
    }
  }
  class SaveListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      projectData.projectName = projectNameText.getText();
      ProjectDataOperate.saveProjectData(projectData);
    }
  }
}

