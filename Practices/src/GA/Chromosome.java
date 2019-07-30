//This source file defines a indivisual of Group
//it is composed by a arraylist which has four directions
//it also can mutation by itself
import java.util.*;

public class Chromosome{
  private ArrayList<Integer> list;
  private ArrayList<Integer> path;
  //the fitness of the path
  double fitness;
  final Map map;
  private double mutationRate;
  private int length;
  //create a new one
  public Chromosome(int length,double mu, Map m){
    mutationRate = mu;
    this.length = length;
    list = new ArrayList<>();
    map = m;
    for(int i=0; i<length; i++){
      list.add((int)(Math.random()*4));
    }
  }
  public ArrayList<Integer> getList(){
    return list;
  }
  public void setList(ArrayList<Integer> a){
    list = a;
  }
  public ArrayList<Integer> getPath(){
    return path;
  }
  public void setPath(ArrayList<Integer> a){
    path = a;
  }
  //mutate by probability of mutation rate
  public void mutate(){
    for(Integer i: list){
      if(Math.random()<mutationRate) i=(1+(int) i+(int) (Math.random()*3)) % 4;
    }
  }
  //show the message easily
  public String toString(){
    String s = "list = " + list + "\n" + "path = " + path + "\n" + "fitness = "+ fitness + "\n";
    return s;
  }
  //a method to evolve faster, I use this to discard the useless gene
  //but I've no confidence about it's corretness
  public void update(){
    if(path == null) return;
    list = path;
    int i = list.size();
    if(fitness < 1){
      for(;i<length;i++){
        list.add((int) (Math.random() * 4));
      }
    }
  }
}
