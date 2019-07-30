import java.util.*;
import java.io.*;

public class GuiPagesGuiActor implements SeiteiPanelActor, Serializable{
  public static final long serialVersionUID = 1l;
  public void action(SeiteiPanel sp, ProjectData pd, ArrayList<Protocol> pl, String s){
    ArrayList<GuiPage> guiPages = pd.guiPages;
    GuiPage guiPage = null;
    for(GuiPage gp: guiPages){
      if(s.equals(gp.name)) guiPage = gp;
    }
    if(s.equals("Add New")) guiPage = new GuiPage("New Gui Page");
    else if(guiPage == null) return;
    new GuiPageSetGui(sp, pd, guiPage).setGui();
  }
  public ArrayList<String> getButtonNameList(ProjectData pd){
    ArrayList<GuiPage> gp = pd.guiPages;
    ArrayList<String> strings = new ArrayList<>();
    for(GuiPage g: gp){
      strings.add(g.name);
    }
    return strings;
  }
}
