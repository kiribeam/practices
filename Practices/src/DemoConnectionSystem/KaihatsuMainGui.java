import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

public class KaihatsuMainGui{
  private JFrame developMainFrame = new JFrame("Ruri Kaihatsu System!           ルリ開発システム！");
  private ProjectData projectData = null;

  public static void main(String[] args){
    new KaihatsuMainGui().setMainGui();
  }

  public void setMainGui(){
    JButton newProjectButton = new JButton("New Project");
    JButton selectProjectButton = new JButton("Select Project");
    JButton delProjectButton = new JButton("delete Project");
    JButton setProjectButton = new JButton("Project setting");
    JButton runProjectButton = new JButton("Run Current Project");


    newProjectButton.addActionListener(new NewProjectActionListener());
    selectProjectButton.addActionListener(new SelectProjectActionListener());
    delProjectButton.addActionListener(new DeleteProjectActionListener());
    setProjectButton.addActionListener(new SettingProjectActionListener());
    runProjectButton.addActionListener(new RunProjectActionListener());

    JPanel showMessagePanel = new ShowMessagePanel();
    JPanel buttonPanel = new ButtonPanel();

    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    showMessagePanel.setBackground(Color.darkGray);

    buttonPanel.add(newProjectButton);
    buttonPanel.add(selectProjectButton);
    buttonPanel.add(delProjectButton);
    buttonPanel.add(setProjectButton);
    buttonPanel.add(runProjectButton);

    showMessagePanel.setFocusable(false);

    developMainFrame.getContentPane().add(BorderLayout.EAST, buttonPanel);
    developMainFrame.getContentPane().add(BorderLayout.CENTER, showMessagePanel);

    developMainFrame.setSize(700,380);
    developMainFrame.setVisible(true);
    developMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

 class ShowMessagePanel extends JPanel{
    public static final long serialVersionUID = 1L;
    public void paintComponent(Graphics gg){
      Graphics2D g = (Graphics2D) gg;
      g.setBackground(Color.black);
      g.setColor(Color.darkGray);
      g.fillRect(0,0,1000,1000);
      g.setColor(Color.white);
      g.setFont(new Font("TimesRoman",Font.PLAIN, 30));
      g.drawString("Welcome to Ruri Dev System!",90,50);
      g.drawString("ルリ～☆彡", 200, 150);
      g.drawString("Current Project :", 30, 230);
      String currentProject = "";
      if(projectData == null) {
        currentProject = "Select Or Create New!";
        g.drawString(currentProject, 90,290);
        return;
      }
      currentProject = projectData.projectName;
      g.drawString(currentProject, 300,230);
      g.setFont(new Font("TimesRoman",Font.PLAIN, 20));

      g.drawString("DataBases :" + projectData.dataBases.size(),40,250);
      g.drawString("Socket Devices :" + projectData.socketDevices.size(),40,270);
      g.drawString("Other Devices:" + projectData.otherDevices.size(),40,290);
      g.drawString("Gui Pages:" + projectData.guiPages.size(),40,310);
      //or show var numbers..
    }
  }
  class ButtonPanel extends JPanel{
    public static final long serialVersionUID = 1L;
  }

  public void openSetGui(){
    developMainFrame.setVisible(false);
    new SetMainGui(projectData).setGui();
  }

  class NewProjectActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      //jmp to setting with null project data
      openSetGui();
    }
  }
  class SelectProjectActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      selectFile();
    }
  }
  private void selectFile(){
    JFileChooser fileOpen = new JFileChooser();
    fileOpen.setFileFilter(new FileFilter(){
      @Override
      public boolean accept(File f){
        if(f.isDirectory()) return true;
        return f.getName().endsWith(".ruri");
      }
      @Override
      public String getDescription(){
        return ".ruri";
      }
    });
    fileOpen.showOpenDialog(developMainFrame);
    projectData = ProjectDataOperate.loadProjectData(fileOpen.getSelectedFile());
    developMainFrame.repaint();
  }
  class DeleteProjectActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      JOptionPane.showMessageDialog(null, "Do by your hands hahah!", "Delete Project", JOptionPane.WARNING_MESSAGE);
    }
  }
  class SettingProjectActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      if(projectData == null){
        int n = JOptionPane.showConfirmDialog(null, "New project?",
            "Help", JOptionPane.YES_NO_OPTION);
        if(1 == n) return;
        //jmp new frame with new project data
      }else{
        //jmp frame with selected data
        projectData.update();
        openSetGui();
      }
    }
  }
  class RunProjectActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent ae){
      if(projectData == null) selectFile();
      if(projectData == null) return;
      developMainFrame.dispose();
      new Thread(new SeigyoSystem(projectData)).start();
      new Thread(new SeigyoSystemGui(projectData, 500)).start();
    }
  }
}
