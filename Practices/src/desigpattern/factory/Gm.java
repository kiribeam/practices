package desigpattern.factory;
public class Gm implements MS{
  public final String type = "Gm";
  private String name;
  public String getName(){
    return name;
  }
  public void setName(String n){
    name = n;
  }
  public String getType(){
    return type;
  }
}
