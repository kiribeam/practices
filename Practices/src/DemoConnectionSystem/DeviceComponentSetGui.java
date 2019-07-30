import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class DeviceComponentSetGui extends ComponentSetGui{
  private ArrayList<Device> arrayList;
  public DeviceComponentSetGui(GuiPageSetGui g, GuiPageComponent c, ProjectData pd ,GuiPage gp){
    super(g, c, pd, gp);
  }

  public void  setGui(){
    JLabel deviceLabel = new JLabel("Device Select");
    arrayList = new ArrayList<>();
    for(Device d: projectData.socketDevices){
      arrayList.add(d);
    }
    for(Device d: projectData.otherDevices){
      arrayList.add(d);
    }
    String[] names = NamedDataOperate.getNamedDataNames(arrayList);

    list = new JList<>(names);
    list.setVisibleRowCount(4);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    if(!(((GuiPageDeviceControlComponent)component).device == null)) {
      String deviceName = ((GuiPageDeviceControlComponent)component).device.name;
      for(int i=0; i<names.length; i++){
        if(deviceName.equals(names[i]))
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

    JLabel nameLabel = new JLabel(component.name);
    panel.add(nameLabel);
    panel.add(nameText);
    panel.add(deviceLabel);
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
      String deviceString = list.getSelectedValue();
      for(Device d: arrayList){
        if(d.name.equals(deviceString))
          ((GuiPageDeviceControlComponent)component).device = d;
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
