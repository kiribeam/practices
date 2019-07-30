import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class GuiPageDeviceControlComponent extends GuiPageComponent {
  public static final long serialVersionUID = 1l;
  public Device device;
  public JTextField inputText;
  public JButton sendButton;
  public Var var;
  public GuiPageDeviceControlComponent(String name, GuiPage gp, Device d){
    super(name, gp, 0);
    device = d;
    var = new Var("none");
    var.value = "Look At Me";
    panel = new DeviceControlPanel();
  }
  class DeviceControlPanel extends JPanel implements ActionListener{
    public static final long serialVersionUID = 1l;
    public DeviceControlPanel(){
      setSize(300,300);
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


      JLabel warnLabel = new JLabel("Here input the command -- take care of your hand!");
      add(warnLabel);
      JLabel hintLabel = new JLabel("Use : Type:amount:addr (like create var)");
      add(hintLabel);

      inputText = new JTextField(20);
      inputText.setText("");
      inputText.setMaximumSize(new Dimension(200,30));
      add(inputText);

      sendButton = new JButton("Send it to device");
      sendButton.addActionListener(this);
      add(sendButton);
    }
    @Override
    public void paintComponent(Graphics g){
      setBackground(Color.white);
      g.setColor(Color.green);
      g.fillRect(0, 0, 300, 300);
      g.setColor(Color.black);
      if(device == null) return;
      g.drawString("Device:  " + device.name, 5, 120);
      g.drawString("Device status: " + device.status,100,120);
      if(var == null) return;
      //System.out.println("Have no var value in DCComponent");
      result = var.value;
      g.drawString("Result is :" + result,10,180);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
      var = new Var(name + ".tmp");
      String tmp = inputText.getText();
      if(tmp.equals("")) return;
      String[] tmps = tmp.split(":");
      try{
        var.amount = Integer.parseInt(tmps[0]);
        var.type = Integer.parseInt(tmps[1]);
        var.addr = tmps[2];
      }catch(Exception e){
        inputText.setText("wrong input!");
        return;
      }
      device.addVar(var);
    }
  }
}
