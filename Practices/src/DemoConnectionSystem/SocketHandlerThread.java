import java.util.*;
import java.io.*;
import java.net.Socket;

public class SocketHandlerThread implements Runnable{
  private Socket client;
  //private main class to get flag
  public ArrayList<Var> varList;
  private ArrayList<Var> randomReadList;
  private ArrayList<Var> randomWriteList;
  private ArrayList<Var> commandList;
  public Protocol protocol;
  public int time;
  private SocketDevice sd;

  public SocketHandlerThread(Socket client, SocketDevice sd){
    this.client = client;
    this.varList = sd.varList;
    this.protocol = sd.protocol;
    this.time = sd.time;
    this.sd = sd;
  }

  @Override
  public void run(){
    try{
      OutputStream outStream = client.getOutputStream();
      PrintWriter printWriter = new PrintWriter(outStream);
      InputStream inStream = client.getInputStream();
      BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inStream));
      System.out.println("set time is : " + time);
      Timer timer = new Timer();
      timer.schedule(new Task( printWriter, bufferedReader, inStream, timer), new Date(),time);
    }catch (IOException ex){
      sd.status = false;
      try{
        client.close();
      }catch(Exception e){
        System.out.println("socket close err");
      }
      //ex.printStackTrace();
      System.out.println("wrong in System buffer stream");
    }catch(Exception e){
      e.printStackTrace();
      System.out.println("Unknown err in timer");
    }
  }
  class Task extends TimerTask{
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private InputStream is;
    private Timer t;
    private int failCount = 0;
    public Task(PrintWriter printWriter,
        BufferedReader bufferedReader, InputStream is, Timer t){
      this.printWriter = printWriter;
      this.bufferedReader = bufferedReader;
      this.is = is;
      this.t = t;
    }
    @Override
    public void run(){
      System.out.println(sd.status);
      if(!sd.status) {
		for(Var v: sd.varList)
			v.value = "Disconected!";
        cancel();
        this.t.cancel();
        System.gc();
      }
      singleSend(varList);
    }
    private void singleSend(ArrayList<Var> vl){
      if(vl == null) return;
      Iterator<Var> iter = vl.iterator();
      Var tmpVar;
      String tmpString = "";
      while(iter.hasNext() && sd.status){
        tmpVar = iter.next();
        if(tmpVar.type > 3) continue;
        tmpString = protocol.send(tmpVar);
        if(tmpString == null) {
          failCount ++;
          continue;
        }
        System.out.println("Now send is " + tmpString);
        printWriter.write(tmpString);
        printWriter.flush();
        tmpString = "";
        try{
          if(protocol.endType == 0 && protocol.endString.equals("\n"))
            tmpString = bufferedReader.readLine();
          else if(protocol.endType == 0) {
            while(true){
              int current = is.available();
              char[] buffer = new char[current];
              bufferedReader.read(buffer, 0, current);
              tmpString += (new String(buffer));
              if(tmpString.endsWith(protocol.endString))
                break;
            }
          }else if(protocol.endType == 1){
            int headLength = protocol.headLength;
            char[] head = new char[headLength];
            bufferedReader.read(head, 0, headLength);
            tmpString = new String(head);
            int restLength = protocol.frameLength(tmpString);
            if(restLength < 0) throw new Exception();
            char[] rest =  new char[restLength];
            bufferedReader.read(rest, 0, restLength);
            tmpString += new String(rest);
            System.out.println("now Get the response head is " + tmpString);
          }else {
            throw new Exception();
          }
          //hold here later finish
          //and I'll reload the read function to fix different type
          //use read a char and make a choose or use other way to keep reading !
          if(tmpString == null) {
            System.out.println("tmpString == null");
            sd.status = false;
            throw new Exception();
          }
          tmpString = protocol.getResponse(tmpString);
          if(tmpString == null) {
            failCount ++;
            continue;
          }
          if(tmpVar.type == 0) tmpVar.update(tmpString);
          else if(tmpVar.type == 1) tmpVar.type = 5;
          else if(tmpVar.type == 2 || tmpVar.type == 3){
            tmpVar.type = 5;
            tmpVar.update(tmpString);
          }else {
            tmpVar.type++;
            VarOperate.removeUseless(sd.varList);
          }
          //only read can update value
          //System.out.println("Now changed Var :" + tmpVar.name +
           //  "   Value as : " + tmpVar.value );
        }catch(IOException ex){
          sd.status = false;
          System.out.println("Stream closed");
          try{
            client.close();
          }catch(IOException e){
            System.out.println("Just close socket err, WHO cares");
          }
        }catch(Exception e){
          failCount ++;
          e.printStackTrace();
          System.out.println("wrong in countpart");
        }
      }
      if(failCount > 100) {
        sd.status = false;
        System.out.println("bad"); //write in log;
      }
      //write part will be same
    }
  }
}
