public class DecorateVar extends NamedData{
  public static final long serialVersionUID = 1l;
  public Var var;
  public int startPosition;
  public char type;// I int32 D double64 s string? B byte8 b boolean1 S short16 F float32 L long64
  public boolean code; // Binary Asc
  public boolean endian; // Big Small
  public int length; //help String
  public DecorateVar(Var var){
    this.var = var;
    name = "D(" + var.name + ")";
    startPosition = 0;
    type = 'I';
    code = false;
    endian = false;
    length = '4';
    setLength();
  }
  public DecorateVar(Var var, int sp, char t, boolean c, boolean b){
    this.var = var;
    name = "D(" + var.name + ")";
    startPosition = sp;
    type = t;
    code = c;
    endian = b;
    length = '4';
    setLength();
  }
 
  public static void main(String[] args){
    Var var  = new Var("xx");
    var.value = "75d256806a737f40";
    DecorateVar dvar = new DecorateVar(var, 0, 'D', true, true);
    System.out.println(dvar.getValue());
    var.value = "\0\0AB";
    dvar = new DecorateVar(var,0,'I',false ,false);
    System.out.println(dvar.getValue());
  }
  public String getValue(){
    String tempString = getCut();
    if(!code && type == 's') return tempString;
    if(length == 1) return tempString;

    byte[] bytes;
    if(!code){
       bytes = getBinBytes(tempString);
    }else{
       bytes = getAscBytes(tempString);
    }
    String value;
    switch(type){
      case 'I':
        value = "" + transInt(bytes); break;
      case 'S':
        value = "" + transShort(bytes);break;
      case 'F':
        value = "" + transFloat(bytes);break;
      case 'D':
        value = "" + transDouble(bytes);break;
      case 'L':
        value = "" + transLong(bytes);break;
      case 's':
        value = "" + transString(bytes);break;
      case 'B':
        value = "" + transByte(bytes);break;
      default:
        value = "ERR";
    }
    return value;
  }
  public void setLength(){
    switch(type){
      case 'b':
      case 'B': length = 1; break;
      case 'S': length = 2; break;
      case 'I':
      case 'F': length = 4; break;
      case 'D':
      case 'L': length = 8; break;
    }
    if(code && type != 'b') length *= 2;
  }
  private String getCut(){
    String tempValue = var.value;
    tempValue = tempValue.substring(startPosition, length);
    return tempValue;
  }
  private byte[] getAscBytes(String ascString){
    int size = ascString.length();
    byte[] bytes;
    if(size == 1){
      bytes = new byte[1];
      int value = Integer.parseInt(ascString, 16);
      bytes[0] = (byte) (value & 0xF);
    }else{
      bytes = new byte[size/2];
      for(int i=0; i<size ; i+=2){
        int value = Integer.parseInt(ascString.substring(i, i+2), 16);
        bytes[i/2] = (byte)(value & 0xFF);
      }
    }
    return bytes;
  }
  private byte[] getBinBytes(String binString){
    int size = binString.length();
    byte[] bytes = new byte[size];
    for(int i=0; i<size; i++){
      bytes[i] = (byte) (binString.charAt(i) & 0xFF);
    }
    return bytes;
  }
  private long getLongBits(byte[] bytes){
    long l = 0;
    int size = bytes.length;
    if(!endian){
      for(int i=0; i<size; i++){
        l |= ((long) (bytes[i] & 0xff)) << ((size-i-1) * 8);
      }
    }else {
      for(int i=0; i<size; i++){
        l |= ((long) (bytes[i] & 0xff)) << (8 * i);
      }
    }
    return l;
  }

  private long transLong(byte[] bytes){
    return getLongBits(bytes);
  }
  private short transShort(byte[] bytes){
    return (short) getLongBits(bytes);
 }
  private int transInt(byte[] bytes){
    return (int) getLongBits(bytes);
 }
  private float transFloat(byte[] bytes){
   return Float.intBitsToFloat((int) getLongBits(bytes));
  }
  private double transDouble(byte[] bytes){
    return Double.longBitsToDouble(getLongBits(bytes));
  }
  private byte transByte(byte[] bytes){
    return (byte) getLongBits(bytes);
  }
  private String transString(byte[] bytes){
    String s = "";
    for(byte b: bytes){
      s += (char)(b);
    }
    return s;
  }
}
