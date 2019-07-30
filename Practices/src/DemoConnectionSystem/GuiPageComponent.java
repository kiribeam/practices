import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public abstract class GuiPageComponent extends NamedData{
  public static final long serialVersionUID = 1l;
  public Dimension position;// kansei shitakunai
  public JPanel panel;
  public GuiPage guiPage;
  public String result;
  public final int type;//0 device 1 message
  public GuiPageComponent(String name, GuiPage gp, int type){
    this.name = name;
    guiPage = gp;
    result = "";
    this.type = type;
  }
  public void repaint(){
    panel.repaint();
  }
}
