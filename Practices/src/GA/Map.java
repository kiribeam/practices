//This source file intents to create a map
public class Map{
  final int width;
  final int height;
  final Block[][] blocks;
  private int[] start = {0, 0};
  private int[] goal = {0, 0};
  public Map(int[][] flag){
    //System.out.println(flag.length);
    width = flag[0].length;
    height = flag.length;
    blocks = new Block[height][width];
    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        if(flag[i][j] == 5) setStart(i,j);
        if(flag[i][j] == 8) setGoal(i,j);
        blocks[i][j] = new Block(this,i,j,checkFlag(flag[i][j]));
      }
    }
  }
  public boolean canMove(int x, int y){
    return blocks[x][y].canMove();
  }
  public int getDistance(int x, int y){
    return Math.abs(x-goal[0]) + Math.abs(y - goal[1]);
  }
  private void setStart(int x, int y){
    start[0] = x;
    start[1] = y;
  }
  private void setGoal(int x, int y){
    goal[0] = x;
    goal[1] = y;
  }
  private static boolean checkFlag(int i){
    if(i == 1) return false;
    else return true;
  }
  //return the start point and goal
  public int[][] getSG(){
    int[][] a = new int[2][];
    a[0] = start;
    a[1] = goal;
    return a;
  }
}
