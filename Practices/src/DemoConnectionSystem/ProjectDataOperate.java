import java.util.*;
import java.io.*;

public class ProjectDataOperate{
  public static void main(String[] args){
    ProjectDataOperate.createNewProjectData("TestProject");
    ProjectDataOperate.createNewProjectData("TestProject2");
    ProjectDataOperate.createNewProjectData("TestProject");
    if(ProjectDataOperate.loadProjectData("ProjectData/TestProject") != null) 
      System.out.println("ok");
    else 
      System.out.println("bu ok");
  }

  public static boolean createNewProjectData(String projectName){
    ProjectData projectData = new ProjectData(projectName);
    File dir = new File("ProjectData");
    String pathString = "";
    if(!dir.exists()){
      if(!dir.mkdir()){
        System.out.println("ERR in mkdir");
        return false;
      }
    }
    pathString = dir.getAbsolutePath();
    File projectDataFile = new File(pathString + "/" + projectName + ".ruri");
    if(projectDataFile.exists()) {
      System.out.println("file already exists");
      return true;
    }

    try{
      FileOutputStream fs = new FileOutputStream(projectDataFile);
      ObjectOutputStream os = new ObjectOutputStream(fs);
      os.writeObject(projectData);
      os.close();
      return true;
    }catch(IOException e){
      e.printStackTrace();
      return false;
   }
  }

  public static ProjectData loadProjectData(String path){
    ProjectData projectData = null;
    try{
      FileInputStream fi = new FileInputStream(path);
      ObjectInputStream is = new ObjectInputStream(fi);
      projectData = (ProjectData) is.readObject();
    }catch(Exception e){
      e.printStackTrace();
      System.out.println("Unknown err @ read projectdata");
    }
    return projectData;
  }
  public static ProjectData loadProjectData(File f){
    ProjectData projectData = null;
    try{
      FileInputStream fi = new FileInputStream(f);
      ObjectInputStream is = new ObjectInputStream(fi);
      projectData = (ProjectData) is.readObject();
    }catch(Exception e){
      e.printStackTrace();
      System.out.println("Unknown err @ read projectdata");
    }
    return projectData;

  }

  public static boolean delProjectData(String path){
    File projectDataFile = new File(path);
    return projectDataFile.delete();
  }

  public static boolean addSocketDevice(ProjectData pd, 
      SocketDevice sd){
    for(SocketDevice s: pd.socketDevices){
      if(s.name.equals(sd.name)){
        System.out.println("Same named device exists!");
        return false;
      }
    }
    pd.socketDevices.add(sd);
    updateVarTable(pd);
    return true;
  }
  public static boolean delSocketDevice(ProjectData pd,
      SocketDevice sd){
    for(SocketDevice s : pd.socketDevices){
      if(s.name.equals(sd.name)){
        pd.socketDevices.remove(s);
        updateVarTable(pd);
        return true;
      }
    }
    System.out.println("Unfinded required device");
    return false;
  }
  //add other device
  //add gui
  //add protocols

  public static boolean updateVarTable(ProjectData pd){
    //two ways : one is update all to have more generate attitude
    //the other is to change that changed vars but it may not faster
    return true;
  }
  public static boolean saveProjectData(ProjectData pd){
    ProjectData projectData = pd;
    File dir = new File("ProjectData");
    String pathString = "";
    if(!dir.exists()){
      if(!dir.mkdir()){
        System.out.println("ERR in mkdir");
        return false;
      }
    }
    pathString = dir.getAbsolutePath();
    File projectDataFile = new File(pathString + "/" + pd.projectName + ".ruri");
    try{
      FileOutputStream fs = new FileOutputStream(projectDataFile);
      ObjectOutputStream os = new ObjectOutputStream(fs);
      os.writeObject(projectData);
      os.close();
      return true;
    }catch(IOException e){
      e.printStackTrace();
      return false;
   }
  }
}
