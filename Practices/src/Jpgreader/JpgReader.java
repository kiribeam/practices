/*This source file(JpgReader0.80alpha) is used to create a
  Java program which is used to read the property of JPG/JPEG 
  and show the message by Java.Swing window. 
  Typed by Kiri at 2017/05/31.*/
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileFilter;

public class JpgReader {
  private JFrame frame;// Main frame
  private MyMPanel mPanel;// Main Panel;
  private MyBPanel bPanel;// Button Panel;
  private File jpgFile;
  private int[] message = new int[6];
  // Test drive
  public static void main(String[] args) {
    new JpgReader().run();
  }
  //Set default frame and run
  public void run(){
    setGui();
  }
  //Set Gui 
  public void setGui(){
    frame = new JFrame();
    bPanel = new MyBPanel();
    //set a button panel to control
    bPanel.setPreferredSize(new Dimension(100,500));
    JButton readBt = new JButton("Read JPG/JPEG");
    readBt.addActionListener(new ReadListener());
    //add a listener to show dialog
    readBt.setMaximumSize(new Dimension(50,20));
    bPanel.add(readBt);
    mPanel = new MyMPanel();
    //set a panel to show the picture
    mPanel.setPreferredSize(new Dimension(2000,2000));
    //add the picture on a scroller to show it well
    JScrollPane scroller = new JScrollPane(mPanel);
    scroller.setMaximumSize(new Dimension(5,5));
    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    frame.getContentPane().add(BorderLayout.EAST, bPanel);
    frame.getContentPane().add(BorderLayout.CENTER, scroller);
    frame.setSize(800,450);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("JpgReader");
  }
  // Show picture and message on this
  class MyMPanel extends JPanel{
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      Image image;
      g2.setColor(Color.black);
      g2.fillRect(0,0,2000,2000);
      //draw the picture
      if(jpgFile != null) {
        if(jpgFile.exists()) {
          image = new ImageIcon(jpgFile.getAbsolutePath()).getImage();
          g2.drawImage(image,4,3,this);
          g2.setColor(Color.darkGray);
          g2.setFont(new Font("s", Font.PLAIN, 20));
          String[] s = {"null","per inch", "per cm"};
          g2.drawString("version "+message[0]+ "  density " + s[message[1]] + "   X_density" + message[2] + "  Y_density " + message[3] + "    height " + message[4]  +"  width " +  message[5], 50, 80);
        }
      } else {
        //the default theme to hint
        g2.setColor(Color.darkGray);
        g2.setFont(new Font("s", Font.PLAIN, 30));
        g2.drawString("Press 'Read' button to select image",50,350);
      }
      //show the message
   }
  }
  // Show basic functions
  class MyBPanel extends JPanel{
    ;
  }
  class ReadListener implements ActionListener{
    public void actionPerformed(ActionEvent a){
      ChooseDialog c = new ChooseDialog(frame);
      //use a customized filechooser
      String[] s = {".jpg", ".jpeg"};
      //fill in the file type
      try{
        File tempFile = c.selectFile(s);
        if(tempFile != null) jpgFile = tempFile;
      } catch(Exception e) {
        ;
      }
      try{
        TwoList twoList = new CodeChange(jpgFile).readFile();
        //get the message
        message = twoList.getMessage();
      }catch(Exception e) {
        ;
      }
      mPanel.repaint();
    }
  }
}
