import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class SeigyoSystemGui implements ActionListener, Runnable{
  private JFrame frame = new JFrame("Now Loading...(<_<B/\\)");
  private ArrayList<GuiPage> guiPages;
  private JPanel buttonPanel;
  private JPanel cardPanel;
  private CardLayout cardLayout;
  private GuiPage currentPage;
  private int time = 500;

  public SeigyoSystemGui(ProjectData pd, int time){
    this.guiPages = pd.guiPages;
    this.time = time;
  }

  public void setGui(){
    if(guiPages.size() == 0) return;
    currentPage = guiPages.get(0);
    buttonPanel = new JPanel();
    cardPanel = new JPanel();
    cardLayout = new CardLayout();
    cardPanel.setLayout(cardLayout);

    for(GuiPage gp: guiPages){
      JButton button = new JButton(gp.name);
      button.addActionListener(this);
      buttonPanel.add(button);
      cardPanel.add(gp.name,gp.panel);
      System.out.println("Now one");
    }

    cardPanel.setSize(1000,700);
    buttonPanel.setSize(1000,100);
    buttonPanel.setBackground(Color.pink);

    frame.getContentPane().add(BorderLayout.NORTH, buttonPanel);
    frame.getContentPane().add(BorderLayout.CENTER, cardPanel);
    frame.setSize(1000, 800);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    while(true){
      currentPage.repaint();
      frame.repaint();
      try{
        Thread.sleep(time);
        //System.out.println("now wakeup at SeigyoGui");
      }catch(Exception ex){
        System.out.println("Gui repaint sleep failed");
      }
    }
  }
  @Override
  public void actionPerformed(ActionEvent ae){
    JButton tempButton = (JButton) ae.getSource();
    String text = tempButton.getText();
    currentPage = (GuiPage) NamedDataOperate.getNamedData(guiPages, text);
    cardLayout.show(cardPanel, text);
  }

  @Override
  public void run(){
    setGui();
  }
}
