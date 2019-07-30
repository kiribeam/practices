import java.util.*;
import java.io.*;

public abstract class NamedData implements Serializable, Cloneable{
  public static final long serialVersionUID =1l;
  public String name;
  public String getName(){
    return name;
  }
  @Override
  public String toString(){
    return name;
  }
  @Override
  protected NamedData clone() throws CloneNotSupportedException{
    return (NamedData) super.clone();
  }
}
