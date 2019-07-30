import java.util.*;

public interface SeiteiPanelActor{
  public void action(SeiteiPanel sp, ProjectData pd, ArrayList<Protocol> pl, String buttonString);
  public ArrayList<String> getButtonNameList(ProjectData pd);
}
