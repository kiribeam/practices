import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class SystemVarSetGui implements ActionListener{

  public static final long serialVersionUID = 1l;
  private SeiteiPanel seiteiPanel;
  private ProjectData projectData;
  private SystemVar systemVar;
  private JFrame frame;
  private JPanel mainPanel;
  private CurrentComponentPanel currentPanel;
  private JPanel selectPanel;
  private JButton saveButton;
  private JButton delButton;
  private JTextField nameText;
  private JTextField typeText;
  private JTextField expressionText;

  public SystemVarSetGui(SeiteiPanel sp, ProjectData pd, SystemVar sv){
    seiteiPanel = sp;
    projectData = pd;
    systemVar = sv;


  }
  public void setGui(){
    frame = new JFrame("System Var Set");
    selectPanel = new JPanel();

    mainPanel = new JPanel();

    JLabel nameLabel = new JLabel("System Var name:");
    nameText = new JTextField(systemVar.name,20);
    JLabel expressionLabel = new JLabel("Expression: ");
	JLabel typeLabel = new JLabel("Type");
	typeText = new JTextField(systemVar.amount + "" , 1);
    JLabel hintLabel = new JLabel("Use ($[VarNum])");
    expressionText = new JTextField(systemVar.expression, 60);

    saveButton = new JButton("Save");
    saveButton.addActionListener(this);

    delButton = new JButton("Delete");
    delButton.addActionListener(this);


    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(nameLabel);
    mainPanel.add(nameText);
	mainPanel.add(typeLabel);
	mainPanel.add(typeText);
    mainPanel.add(expressionLabel);
    mainPanel.add(hintLabel);
    mainPanel.add(expressionText);
    mainPanel.add(saveButton);
    mainPanel.add(delButton);

    selectPanel.setMinimumSize(new Dimension(800, 200));
    selectPanel.setSize(800,300);
    for(Device d: projectData.otherDevices){
      selectPanel.add(new SelectComponentPanel(d));
    }
    for(Device d: projectData.socketDevices){
      selectPanel.add(new SelectComponentPanel(d));
    }

    currentPanel = new CurrentComponentPanel();

    frame.getContentPane().add(BorderLayout.NORTH, selectPanel);
    frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
    frame.getContentPane().add(BorderLayout.SOUTH, mainPanel);
    frame.setSize(800,600);
    frame.setVisible(true);
  }
  public void reflesh(){
    NamedDataOperate.removeRepeated(systemVar.varList);
    currentPanel.reflesh();
  }
  @Override
  public void actionPerformed(ActionEvent ae){
    JButton tempButton = (JButton) ae.getSource();
    String buttonString = tempButton.getText();
    int x = JOptionPane.showConfirmDialog(null, "Change? ",
       "Set System Var", JOptionPane.YES_NO_OPTION );
    ArrayList<Var> systemVars = projectData.systemDevice.varList;
    if(1 == x) return;
    if(buttonString.equals("Save")){
      systemVar.name = nameText.getText();
	  try{
		  int i = Integer.parseInt(typeText.getText());
		  systemVar.amount = i;
	  }catch(Exception ex){
	      systemVar.amount = 0;
	  }
      systemVar.expression = expressionText.getText();
      if(!systemVars.contains(systemVar)) {
        systemVars.add(systemVar);
      }
   }else if(buttonString.equals("Delete")){
      systemVar.name = "Null";
   }
   NamedDataOperate.removeRepeated(systemVars);
   seiteiPanel.reflesh();
   frame.dispose();
  }
  class ComponentPanel extends JPanel implements ActionListener{
    public static final long serialVersionUID = 1l;
    public JList<NamedData> list;
    public JButton button;
    public ArrayList<? extends NamedData> al;
    public ComponentPanel(ArrayList<? extends NamedData> al){
      this.al = al;
      paint();
   }
    public void reflesh(){
      removeAll();
      paint();
      repaint();
      revalidate();
      frame.repaint();
    }
    public void paint(){
      list = new JList<>(al.toArray(new NamedData[1]));
      list.setVisibleRowCount(5);
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      setSize(250,150);
      JScrollPane scroller = new JScrollPane(list);
      scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scroller.setSize(new Dimension(100, 200)); 
      add(scroller);
      button = new JButton("NN");
      button.addActionListener(this);
      add(button);
    }
    @Override
    public void paintComponent(Graphics g){
      g.setColor(Color.green);
      g.fillRect(0,0,250,150);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
    }
  }
  class SelectComponentPanel extends ComponentPanel{
    public static final long serialVersionUID = 1l;
    private Device device;
    public SelectComponentPanel(Device d){
      super(d.varList);
      device = d;
    }
    @Override
    public void paint(){
      super.paint();
      button.setText("Add");
    }
    @Override
    public void actionPerformed(ActionEvent ae){
      Var var = (Var) list.getSelectedValue();
      DecorateVar dv = new DecorateVar(var);
      dv.name = "Null";
      systemVar.addVar(dv);
      new DecorateVarSetGui(SystemVarSetGui.this, dv).setGui();
    }
  }
  class CurrentComponentPanel extends ComponentPanel{
    public static final long serialVersionUID = 1l;
    public CurrentComponentPanel(){
      super(systemVar.varList);
    }
    @Override
    public void paint(){
      super.paint();
      button.setText("Change");
    }
    @Override
    public void actionPerformed(ActionEvent ae){
      DecorateVar dvar = (DecorateVar) list.getSelectedValue();
      new DecorateVarSetGui(SystemVarSetGui.this, dvar).setGui();
    }
    @Override
    public void paintComponent(Graphics g){
      g.setColor(Color.pink);
      g.fillRect(0,0,2500,1500);
    }
  }
}
