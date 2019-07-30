import java.util.*;

public class TestDrive{
  public static void main(String[] args){
    CmapTsp map = new CmapTsp(20);
    Group group = new Group(0.2, 0.75, map, 40, 2);
    Gui gui = new Gui(group,map);
    gui.setGui();
    for(int i=0;i<500;i++){
      if(Math.abs(group.bestDistance-group.worstDistance) < 50) break;
      group.epoch();
      System.out.println(" now on " + i);
    }
    System.out.println(" best distance is :" + group.bestDistance);
    System.out.println(" worst distance is : " + group.worstDistance);
    System.out.println(" generation is " + group.generation);
    for(int i=0;i<20;i++){
      System.out.println("path is " + group.bestGen.getPath()[i]);
    }
    gui.update();
  }
}

