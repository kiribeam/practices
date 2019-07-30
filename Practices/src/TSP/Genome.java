import java.util.*;

public class Genome{
  private double fitness;
  final CmapTsp map;
  final Group group;
  private int[] path;
  final int length;
  private double distance;

  public Genome(CmapTsp m, Group g){
    map = m;
    group = g;
    ArrayList<City> temp = (ArrayList<City>) map.getCities().clone();
    length = temp.size();
    path = new int[length];
    for(int i=0,y=0;i<length;i++){
      y =(int) (Math.random() * (length - i));
      //System.out.println(" y is " + y);
      path[i] = temp.get(y).id;
      temp.remove(y);
    }
    setDistance();
  }

  public void setFitness(){
    fitness = Run.fitnessOf(this);
  }
  public double getFitness(){
    return fitness;
  }
  public void setDistance(){
    distance = Run.totalDistance(this);
  }
  public double getDistance(){
    return distance;
  }
  public void setPath(int[] p){
    path = p;
    setDistance();
  }
  public int[] getPath(){
    return path;
  }
  public void mutate(double mutateRate){
    if(Math.random() > mutateRate) return;
    int pos1 = (int) (Math.random()* length);
    int pos2 = pos1;
    while(pos2 == pos1){
      pos2 = (int) (Math.random() * length);
    }
    int temp = path[pos1];
    path[pos1] = path[pos2];
    path[pos2] = temp;
    setDistance();
    return;
  }
}
