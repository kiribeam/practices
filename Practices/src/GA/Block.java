public class Block{
  final int x;
  final int y;
  final Map map;
  final boolean posFlag;
  private boolean tempFlag = true;
  public Block(Map m, int x, int y, boolean f){
    this.x = x;
    this.y = y;
    map = m;
    posFlag = f;
  }
  boolean canMove(){
    return (posFlag && tempFlag);
  }
  boolean getFlag(){
    return tempFlag;
  }
  int[] getPosition(){
    int[] a = new int[2];
    a[0]=x;
    a[1]=y;
    return a;
  }
}
