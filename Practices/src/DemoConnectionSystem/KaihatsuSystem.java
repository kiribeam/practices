import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

public class KaihatsuSystem{
  public static void main(String[] args){
    init();
  }
  public static void init(){
    new KaihatsuMainGui().setMainGui();
  }

}
