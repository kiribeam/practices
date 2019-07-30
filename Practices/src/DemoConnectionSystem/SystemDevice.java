import java.util.*;
import java.io.*;

public class SystemDevice extends Device implements Serializable{
  public static final long serialVersionUID = 1l;
  public SystemDevice(){
    super("Ruri System");
    protocol = null;
    time = 200;
  }
}
