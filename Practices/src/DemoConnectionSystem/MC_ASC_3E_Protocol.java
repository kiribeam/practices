import java.util.*;

public class MC_ASC_3E_Protocol extends MCProtocol{
  public static final long serialVersionUID = 1l;
  public RandomAccessProtocolActor pActor = (RandomAccessProtocolActor) actor;
  public MC_ASC_3E_Protocol(){
    super("MC Ascii 3E Protocol", 22, 1, null);
    actorList = new ArrayList<>();
    actorList.add(new MC_ASC_3E_ReadActor());
    actorList.add(new MC_ASC_3E_WriteActor());
  }
  @Override
  public String packedSend(ArrayList<Var> varList){
    return pActor.packedSend(varList);
  }

  @Override
  public ArrayList<String> getPackedResponse(String s){
    return pActor.getPackedResponse(s);
 }

  @Override
  public void changeActor(Var var){
    if(var.type == 0) actor = actorList.get(0);
	else if(var.type == 1) actor = actorList.get(1);
    return;
  }

  @Override
  public int frameLength(String s){
    String l = s.substring(14, 18);
    int length = Integer.parseInt(l, 16) - 4;
    return length;
  }

}
