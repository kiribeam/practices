import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class DecorateVarSetGui implements ActionListener{
  public static final long serialVersionUID = 1l;
  private JFrame frame;
  private JPanel panel;
  private JButton saveButton;
  private JButton delButton;
  private SystemVarSetGui systemVarSetGui;
  private DecorateVar decorateVar;
  private JTextField nameText;
  private JTextField startPositionText;
  private JTextField typeText;
  private JTextField lengthText;
  private JCheckBox codeBox;
  private JCheckBox endianBox;
  public DecorateVarSetGui(SystemVarSetGui svsg, DecorateVar dv){
    systemVarSetGui = svsg;
    decorateVar = dv;
  }
  public void setGui(){
    frame = new JFrame("Set DecorateVar");
    panel = new JPanel();
    saveButton = new JButton("Save");
    saveButton.addActionListener(this);
    delButton = new JButton("Delete");
    delButton.addActionListener(this);

    JLabel nameLabel = new JLabel("Decorate Var name");
    nameText = new JTextField(decorateVar.name, 15);


    JLabel startLabel = new JLabel("Start Position");
    startPositionText = new JTextField(decorateVar.startPosition + "", 15);

    JLabel typeLabel = new JLabel("Type is:Int/Short/boolean/Byte/string/Long/Double/Float");
    typeText = new JTextField(decorateVar.type + "", 1);

    JLabel lengthLabel = new JLabel("Length (only used in string)");
    lengthText = new JTextField(decorateVar.length + "", 8);

    JLabel codeLabel = new JLabel("Binary Or Asc ");
    codeBox = new JCheckBox("ACS?", decorateVar.code);

    JLabel endianLabel = new JLabel("Big Endian or Small");
    endianBox = new JCheckBox("Small?", decorateVar.endian);

    panel.add(saveButton);
    panel.add(delButton);
    panel.add(nameLabel);
    panel.add(nameText);
    panel.add(startLabel);
    panel.add(startPositionText);
    panel.add(typeLabel);
    panel.add(typeText);
    panel.add(lengthLabel);
    panel.add(lengthText);
    panel.add(codeLabel);
    panel.add(codeBox);
    panel.add(endianLabel);
    panel.add(endianBox);

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setSize(200,400);
    frame.getContentPane().add(BorderLayout.CENTER, panel);
    frame.setSize(220, 400);
    frame.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent ae){
    JButton tempButton = (JButton) ae.getSource();
    String buttonString = tempButton.getText();
    int n = JOptionPane.showConfirmDialog(null, buttonString + "? ",
         "Change Decorate Var", JOptionPane.YES_NO_OPTION );
    if(1 == n) return;

    if(buttonString.equals("Save")){
      decorateVar.name = nameText.getText();
      try{
        decorateVar.startPosition = Integer.parseInt(startPositionText.getText());
      }catch(Exception ex){
        decorateVar.startPosition = 0;
      }
      try{
        decorateVar.type = typeText.getText().charAt(0);
      }catch (Exception ex){
        decorateVar.type = 'I';
      }
      try{
        decorateVar.length = Integer.parseInt(lengthText.getText());
        decorateVar.setLength();
      }catch(Exception ex){
        decorateVar.setLength();
      }
      decorateVar.code = codeBox.isSelected();
      decorateVar.endian = endianBox.isSelected();
    }else if(buttonString.equals("Delete")){
      decorateVar.name = "Null";
    }else return;
    frame.dispose();
    systemVarSetGui.reflesh();
  }
}
