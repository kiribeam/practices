import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

public class VarChangeGui implements Serializable, ActionListener{
  public static final long serialVersionUID = 1l;
  private JFrame frame;
  private JPanel buttonPanel;
  private JPanel setPanel;
  private VarSetPanel varSetPanel;
  private Device device;
  private Var var;

  private JTextField nameText, typeText, amountText, addrText, commentText;
  public VarChangeGui(VarSetPanel vsp, Device d, Var v){
    frame = new JFrame("Var Change");
    varSetPanel = vsp;
    device = d;
    var = v;
  }
  public void setGui(){
    buttonPanel = new JPanel();
    JLabel nameLabel = new JLabel("Var name: ");
    nameText = new JTextField(var.name, 15);
    JButton saveButton = new JButton("Save");
    JButton delButton = new JButton("Delete");
    saveButton.addActionListener(this);
    delButton.addActionListener(this);

    buttonPanel.add(nameLabel);
    buttonPanel.add(nameText);
    buttonPanel.add(saveButton);
    buttonPanel.add(delButton);

    setPanel = new JPanel();
    JLabel amountLabel= new JLabel("Amount of Data : ");
    amountText = new JTextField("" + var.amount, 15);

    JLabel typeLabel = new JLabel("Var type: 0-read 1-write 2-command 3-useless");
    typeText = new JTextField("" + var.type, 15);

    JLabel addrLabel = new JLabel("Address is: ");
    JLabel addrInLabel = new JLabel("use 'REGISTER/DATATYPE/ADDRESS' like: 'RG/0/1233'");
    addrText = new JTextField(var.addr, 30);

    JLabel commentLabel = new JLabel("Comment: ");
    commentText = new JTextField(var.comment, 30);

    setPanel.setLayout(new BoxLayout(setPanel, BoxLayout.Y_AXIS));
    setPanel.add(amountLabel);
    setPanel.add(amountText);
    setPanel.add(typeLabel);
    setPanel.add(typeText);
    setPanel.add(addrLabel);
    setPanel.add(addrInLabel);
    setPanel.add(addrText);
    setPanel.add(commentLabel);
    setPanel.add(commentText);

    frame.getContentPane().add(BorderLayout.NORTH, buttonPanel);
    frame.getContentPane().add(BorderLayout.CENTER, setPanel);
    frame.setSize(480,300);
    frame.setVisible(true);

  }
  @Override
  public void actionPerformed(ActionEvent e){
    JButton tempButton = (JButton) e.getSource();
    String buttonString = tempButton.getText();
    ArrayList<Var> vl = device.varList;
    int n = JOptionPane.showConfirmDialog(null, buttonString + "? ",
         "Change SocketDevice", JOptionPane.YES_NO_OPTION );
    if(1 == n) return;

    if(buttonString.equals("Save")) {
      var.name = nameText.getText();
      var.addr = addrText.getText();
      var.comment = commentText.getText();
      var.device = device;
      try{
        var.amount = Integer.parseInt(amountText.getText());
        var.type = Integer.parseInt(typeText.getText());
      }catch (Exception ex){;}
      if(!vl.contains(var)) vl.add(0, var);
    }else if(buttonString.equals("Delete")){
      var.name = "Null";
    }
    VarOperate.removeUseless(vl);
    varSetPanel.reflesh();
    frame.dispose();
  }

}
