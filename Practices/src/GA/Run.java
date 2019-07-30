//This source file defines two methods that used to find the path
import java.util.*;

public class Run{
  //Test the path and set the fitness
  public static void run(Chromosome c){
    ArrayList<Integer> list = c.getList();
    ArrayList<Integer> path = new ArrayList<>();
    Map map = c.map;
    int[][] a = map.getSG();
    int x = a[0][0];
    int y = a[0][1];
    int tx,ty;
    //test next move can be taken or not
    for(Integer i: list){
      switch(i){
        case 0: tx = x;
                tx--;
                if(tx>=0&&map.canMove(tx,y)) {
                  x = tx;
                  path.add(0);
                }
                break;
        case 1: tx = x;
                tx++;
                if(tx<map.height && map.canMove(tx,y)){
                  x = tx;
                  path.add(1);
                }
                break;
        case 2: ty = y;
                ty++;
                if(ty<map.width && map.canMove(x,ty)){
                  y = ty;
                  path.add(2);
                }
                break;
        case 3: ty = y;
                ty--;
                if(ty>=0 && map.canMove(x,ty)){
                  y = ty;
                  path.add(3);
                }
                break;
        default: System.out.println("Array Wrong");
      }
      if(a[1][0] == x && a[1][1] == y) break;
    }
    //give a current path
    c.setPath(path);
    int d = map.getDistance(x,y);
    c.fitness = 1.0/(d+1);
  }
  //convert the path arraylist to a integer array that can be show on the map
  public static int[][] convertPath(ArrayList<Integer> path,Map map){
    int length = path.size();
    System.out.println("The path's length is " + length);
    int[][] location = new int[length][2];
    int[][] st = map.getSG();
    int x = st[0][0];
    int y = st[0][1];
    System.out.println(st[0]);
    for(int i=0; i<length; i++){
      switch(path.get(i)) {
        case 0: location[i][0] = --x;
                location[i][1] = y;
                break;
        case 1: location[i][0] = ++x;
                location[i][1] = y;
                break;
        case 2: location[i][0] = x;
                location[i][1] = ++y;
                break;
        case 3: location[i][0] = x;
                location[i][1] = --y;
                break;
      }
    }
    return location;
  }
}
