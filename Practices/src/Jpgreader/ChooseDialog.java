/* this source file is used to add a file chooser on defined frame and 
set defined type you want to read */
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class ChooseDialog{
  private File file;
  private JFrame frame;
  public ChooseDialog(JFrame f) {
    frame = f;//add on the frame
  }
  public File selectFile(String[] s) {
    JFileChooser fileOpen = new JFileChooser();//add new filechooser
    fileOpen.setFileFilter(new MyFileFilter(s));
    //add a decorated Filefilter that we can input file type quickly
    fileOpen.showOpenDialog(frame);
    file = fileOpen.getSelectedFile();
    return file;
  }
  //a decorated class 
  class MyFileFilter extends FileFilter{
    String[] strings;
    public MyFileFilter(String[] s) {
      strings = s;
      for(int i=0; i<strings.length;i++){
        strings[i] = strings[i].toLowerCase();
      }
    }
    public boolean accept(File f) {
      if(f.isDirectory()) return true;
      for(String s: strings){
        if(f.getName().toLowerCase().endsWith(s)) return true;
      }
      return false;
    }
    public String getDescription(){
      String string = "";
      for(String s: strings) {
        string = string + "(" + s + ")";
      }
      return string;
    }
  }

}
