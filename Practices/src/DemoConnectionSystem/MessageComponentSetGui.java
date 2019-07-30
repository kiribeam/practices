import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MessageComponentSetGui extends ComponentSetGui{
  private JTextField lowText;
  private JTextField highText;
  private ArrayList<Var> arrayList;

  public MessageComponentSetGui(GuiPageSetGui g, GuiPageComponent c, ProjectData pd, GuiPage gp){
    super(g, c, pd, gp);
  }

  @Override
  public void setGui(){
    JLabel nameLabel = new JLabel(component.name);
    JLabel lowLabel = new JLabel("Low bound");
    lowText = new JTextField("" + ((GuiPageMessageShowComponent)component).lowBound, 15);
    JLabel highLabel = new JLabel("High bound");
    highText = new JTextField("" +((GuiPageMessageShowComponent)component).highBound, 15);

    arrayList = projectData.systemDevice.varList;
    String[] names = NamedDataOperate.getNamedDataNames(arrayList);

    list = new JList<>(names);
    list.setVisibleRowCount(4);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    if(!(((GuiPageMessageShowComponent) component).systemVar == null)){
      String varName = ((GuiPageMessageShowComponent)component).systemVar.name;
      for(int i=0; i<names.length; i++){
        if(varName.equals(names[i]))
          list.setSelectedIndex(i);
      }
    }
    JScrollPane scroller= new JScrollPane(list);
    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroller.setSize(new Dimension(100, 200));

    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(this);
    JButton delButton = new JButton("Delete");
    delButton.addActionListener(this);

    panel.add(nameLabel);
    panel.add(nameText);
    panel.add(lowLabel);
    panel.add(lowText);
    panel.add(highLabel); 
    panel.add(highText);
    panel.add(scroller);
    panel.add(saveButton);
    panel.add(delButton);

    panel.setSize(400,300);
    frame.add(panel);
    frame.setSize(400,300);
    frame.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent ae){
    JButton tempButton  = (JButton) ae.getSource();
    String s = tempButton.getText();
    int n = JOptionPane.showConfirmDialog(null, "Change? ",
       "Set Component", JOptionPane.YES_NO_OPTION );
    if(1 == n) return;
    if(s.equals("Save")){
      component.name = nameText.getText();
      try{
        ((GuiPageMessageShowComponent)component).lowBound = Double.parseDouble(lowText.getText());
        ((GuiPageMessageShowComponent)component).highBound = Double.parseDouble(highText.getText());
      }catch(Exception e){
        ;
      }
      String varString= list.getSelectedValue();
      for(Var v: arrayList){
        if(v.name.equals(varString))
          ((GuiPageMessageShowComponent)component).systemVar= (SystemVar) v;
      }
    }else if(s.equals("Delete")){
      component.name = "Null";
      NamedDataOperate.removeRepeated(guiPage.components);
    }else {
      return;
    }
    guiPageSetGui.reflesh();
    frame.dispose();
  }
}
