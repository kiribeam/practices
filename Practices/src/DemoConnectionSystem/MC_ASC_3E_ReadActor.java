import java.io.*;

public class MC_ASC_3E_ReadActor extends MC_ASC_3E_Actor implements Serializable{
  public static final long serialVersionUID = 1l;
  public String send(Var var){
    String[] addrs = var.addr.split("/");
    if(addrs.length != 3) return null;
    String sendString = "";
    int readType = Integer.parseInt(addrs[1]);
    if(readType == 0) 
      sendString += "04010000";
    else if(readType == 1)
      sendString += "04010001";
    sendString += addrs[0];
    while(addrs[2].length()<6)
      addrs[2] = "0" + addrs[2];
    sendString += addrs[2];
    String number = "" + Integer.toHexString(var.amount);
    while(number.length()<4)
      number = "0" + number;
    sendString += number;
    String head = MC_ASC_3E_Operate.createHead(sendString.length());
    sendString = head + sendString;
    return sendString;
  }
}
