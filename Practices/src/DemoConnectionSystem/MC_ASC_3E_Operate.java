public class MC_ASC_3E_Operate{
  public static final String subHeadSend = "5000";
  public static final String subHeadRev = "D000";
  public static final String netNumber = "00";
  public static final String pcNumber = "FF";
  public static final String requestedTargetIONumber = "03FF";
  public static final String requestedTargetNetnum = "00";
  public String requestedLength = "0000";//just show the message
  public static final String cpuCounter = "0010";
  public String command = "0000";
  public String subCommand = "0000";
  public String requestData = "";
  //for response
  //frome responseLength
  public String responseLength = "0000";
  public static String endCode = "0000";
  public String responseData = "";
  //for err
  public String errEndCode = "FFFF";

  //now start combined Data
  //ignore response create!!~~~~~
  public static String createHead(int length){
    if(length > 8170) return null ;
    String s = "" + (Integer.toHexString(length + 4)).toUpperCase();
    while(s.length() < 4)
      s = "0" + s;
    return "500000FF03FF00" + s + "0010";
  }

  public static String cutHead(String s){
    return s.substring(22);
  }
  public static boolean hasErr(String s){
    if(s.length() < 22) return true;
    return !(s.substring(18,22).equals("0000"));
  }

}
