import java.util.*;

public class Test3{
  public static void main(String[] args){
    ProjectData pd = new ProjectData("Test1");
    SocketDevice sd1 = new SocketDevice("Sd1", "127.0.0.1",
        4000, new NullProtocol("test"));
    SocketDevice sd2 = new SocketDevice("Sd2", "127.0.0.1",
        6000, new MC_ASC_3E_Protocol());
    SocketDevice sd3 = new SocketDevice("Sd3-err", "127.0.0.1",
        10000, new MC_ASC_3E_Protocol());
    sd1.time = 1000;
    sd2.time = 1000;


    pd.socketDevices.add(sd1);
    pd.socketDevices.add(sd2);
    pd.socketDevices.add(sd3);
    Var var11 = new Var("var11");
    var11.type = 0;
    var11.addr = "0001";
    var11.device = sd1;
    Var var12 = new Var("var12");
    var12.type = 0;
    var12.addr = "0002";
    var12.device = sd1;
    Var var13 = new Var("var13");
    var13.type = 0;
    var13.addr = "0003";
    var13.device = sd1;

    Var var21 = new Var("var21");
    var21.amount = 1;
    var21.type = 0;
    var21.addr = "X*/0/0001";
    var21.device = sd2;
    Var var22 = new Var("var22");
    var22.amount = 2;
    var22.type = 0;
    var22.addr = "Y*/1/0002";
    var22.device = sd2;
    Var var23 = new Var("var23");
    var23.amount = 3;
    var23.type = 0;
    var23.addr = "RD/0/0003";
    var23.device = sd3;

    Var var31 = new Var("var31");
    var31.type = 0;
    var31.addr = "0001";
    var31.device = sd3;
    Var var32 = new Var("var32");
    var32.type = 0;
    var32.addr = "0002";
    var32.device = sd3;
    Var var33 = new Var("var33");
    var33.type = 0;
    var33.addr = "0003";
    var33.device = sd3;
    ArrayList<Var> rt1 = sd1.varList;
    ArrayList<Var> rt2 = sd2.varList;
    ArrayList<Var> rt3 = sd3.varList;


    rt1.add(var11);
    rt1.add(var12);
    rt1.add(var13);

    rt2.add(var21);
    rt2.add(var22);
    rt2.add(var23);

    rt3.add(var31);
    rt3.add(var32);
    rt3.add(var33);


    SeigyoSystem showTime = new SeigyoSystem(pd);
    showTime.init();
  }
}
