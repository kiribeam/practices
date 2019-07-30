import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

public class SocketDeviceSetGui{
  private JFrame frame = new JFrame("Socket Device Setting");
  private JPanel socketDeviceSetPanel;
  private SeiteiPanel seiteiPanel;
  private ProjectData projectData;
  private SocketDevice socketDevice;
  private JPanel varSetPanel;
  private ArrayList<Protocol> protocolList;
  private JTextField nameText;
  private JTextField ipText;
  private JTextField timeText;
  private JTextField portText;
  private JList<String> pList;
  private String[] protocolNames;

  public SocketDeviceSetGui(SeiteiPanel sp, ProjectData pd, SocketDevice sd, VarSetPanel vp ,ArrayList<Protocol> pl){
    seiteiPanel = sp;
    projectData = pd;
    socketDevice = sd;
    varSetPanel = vp;
    protocolList = pl;
    int size = pl.size();
    protocolNames = new String[size];
    for(int i=0; i<size; i++){
      protocolNames[i] = protocolList.get(i).name;
    }
  }
  public static void main(String[] args){
    ArrayList<Protocol> pl = new ArrayList<>();
    pl.add(new NullProtocol( "null" ));
    SocketDevice sd = new SocketDevice("new", "ss", 1000);
    new SocketDeviceSetGui(null,new ProjectData("ss"),sd, new VarSetPanel(sd), pl ). setGui();
  }
  public void setGui(){
    if(socketDevice == null) {
      socketDevice = new SocketDevice("New Socket Device", "", 1000);
      System.out.println("add new device");
    }
    JPanel socketDeviceSetPanel = new SocketDeviceSetPanel();

    JLabel nameLabel = new JLabel("Device Name:");
    nameText = new JTextField();
    nameText.setColumns(15);
    nameText.setText(socketDevice.name);

    JLabel ipLabel = new JLabel("Host IP:");
    ipText = new JTextField();
    ipText.setColumns(15);
    ipText.setText(socketDevice.hostIP);

    JLabel portLabel = new JLabel("Port:");
    portText = new JTextField();
    portText.setColumns(15);
    portText.setText("" + socketDevice.port);

    JLabel timeLabel = new JLabel("Round Time:");
    timeText = new JTextField();
    timeText.setColumns(15);
    timeText.setText("" + socketDevice.time);

    JLabel protocolLabel = new JLabel("Protocol List:");
    pList = new JList<>(protocolNames);
    pList.setVisibleRowCount(4);
    pList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    int i=0;
    for(;i<protocolNames.length;i++){
      if(socketDevice.protocol.name.equals(protocolNames[i])) break;
    }
    pList.setSelectedIndex(i);
    JScrollPane protocolScroller = new JScrollPane(pList);
    protocolScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    protocolScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    protocolScroller.setSize(new Dimension(100, 200));


    JButton saveButton = new JButton("SAVE");
    saveButton.addActionListener(new SaveListener());
    JButton delButton = new JButton("Delete");
    delButton.addActionListener(new DelListener());

    socketDeviceSetPanel.setLayout(new BoxLayout(socketDeviceSetPanel, BoxLayout.Y_AXIS));

    socketDeviceSetPanel.add(nameLabel);
    socketDeviceSetPanel.add(nameText);
    socketDeviceSetPanel.add(ipLabel);
    socketDeviceSetPanel.add(ipText);
    socketDeviceSetPanel.add(portLabel);
    socketDeviceSetPanel.add(portText);
    socketDeviceSetPanel.add(timeLabel);
    socketDeviceSetPanel.add(timeText);
    socketDeviceSetPanel.add(protocolLabel);
    socketDeviceSetPanel.add(protocolScroller);
    socketDeviceSetPanel.add(saveButton);
    socketDeviceSetPanel.add(delButton);

    frame.getContentPane().add(BorderLayout.EAST, socketDeviceSetPanel );
    JScrollPane varPanelScroller = new JScrollPane();
//    JPanel tmpPanel = new JPanel();
//    tmpPanel.add(varPanelScroller);
    varPanelScroller.setViewportView(varSetPanel);
    frame.getContentPane().add(BorderLayout.CENTER,varPanelScroller);
    frame.setSize(1000,600);
    frame.setVisible(true);

  }

  class SocketDeviceSetPanel extends JPanel{
    public static final long serialVersionUID = 1l;
  }

  class SaveListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      int n = JOptionPane.showConfirmDialog(null, "Save? ",
         "Change SocketDevice", JOptionPane.YES_NO_OPTION );
      if(1 == n) return;
      socketDevice.name = nameText.getText();
      socketDevice.hostIP = ipText.getText();
      try{
        socketDevice.port = Integer.parseInt(portText.getText());
        socketDevice.time = Integer.parseInt(timeText.getText());
      }catch(Exception ex){
        socketDevice.port = 10000;
        socketDevice.time = 200;
      }
      String protocol = pList.getSelectedValue();
      for(Protocol p : protocolList){
        if(p.name.equals(protocol))
          try{
            socketDevice.protocol = (Protocol) p.clone();
          }catch(Exception ex){
            System.out.println("clone failed");
          }
      }
      if(! projectData.socketDevices.contains(socketDevice))
        projectData.socketDevices.add(0,socketDevice);
      projectData.updateSocketDevice();
      seiteiPanel.reflesh();
      frame.dispose();
    }
  }
  public class DelListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      int n = JOptionPane.showConfirmDialog(null, "Delete? ",
         "Change SocketDevice", JOptionPane.YES_NO_OPTION );
      if(1 == n) return;
      if(! socketDevice.name.equals("Add New"))
        socketDevice.name = "Null";
      projectData.updateSocketDevice();
      seiteiPanel.reflesh();
      frame.dispose();
    }
  }
}
