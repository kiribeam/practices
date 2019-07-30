package desigpattern.factory;
public class Zaku implements MS{
  public final String type = "Zaku";
  private String name;
  public String getName(){
    return name;
  }
  public void setName(String s){
    name = s;
  }
  public String getType(){
    return type;
  }
}
