import java.util.*;

public class Group{
  ArrayList<Genome> gens = new ArrayList<>();
  CmapTsp map;
  final double mutationRate;
  final double crossoverRate;
  private double totalFitness;
  double bestDistance;
  double worstDistance;

  private int gensNumber;
  final int eliteNumber;
  int generation = 0;
  Genome bestGen;

  public Group(double mr, double cr,CmapTsp m, int gn, int en){
    mutationRate = mr;
    crossoverRate = cr;
    map = m;
    for(int i=0;i<gn;i++){
      gens.add(createGen());
    }
    gensNumber = gn;
    eliteNumber = en;
    updateFitness();
  }

  public void epoch(){
    ArrayList<Genome> gens2 = new ArrayList<>();
    for(int i=0;i<eliteNumber;i++){
      Genome gen = getBestGen(gens);
      gens2.add(gen);
      gens.remove(gen);
    }
    for(Genome gen: gens2){
      gens.add(gen);
    }
    System.out.println("new gens OK");
    while(gens2.size() < gensNumber){
      Genome mum = rouletteWheel(gens);
      System.out.println(" roulte can be used");
      Genome dad = mum;
      while(dad.equals(mum)){
        dad = rouletteWheel(gens);
      }
      System.out.println(" routte Ok");
      gens2.add(crossover(mum,dad)[0]);
      gens2.add(crossover(mum,dad)[1]);
    }
    gens = gens2;
    mutate();
    updateFitness();
    generation++;
  }

  private Genome rouletteWheel(ArrayList<Genome> genList){
    double totalFitness = 0;
    Genome temp = genList.get(0);
    for(Genome gen: genList){
      totalFitness += gen.getFitness();
      //System.out.println(totalFitness);
    }
    totalFitness *= (Math.random());
    double bullet = 0;
    for(Genome gen: genList){
      bullet += gen.getFitness();
      if(bullet > totalFitness) {
        temp = gen;
        break;
      }
    }
    return temp;
  }


  private Genome getBestGen(ArrayList<Genome> genList){
    Genome temp = genList.get(0);
    for(Genome gen: genList){
      if(gen.getFitness() > temp.getFitness()){
        temp = gen;
      }
    }
    return temp;
  }

  private void updateFitness(){
    double worst = 0;
    double best = gens.get(0).getDistance();
    Genome tempGen = gens.get(0);
    for(Genome gen: gens){
      double dis = gen.getDistance();
      if(dis > worst){
        worst = dis;
      }
      if(dis < best) {
        best = dis;
        tempGen = gen;
      }
    }
    worstDistance = worst;
    bestDistance = best;
    bestGen = tempGen;
    for(Genome gen: gens){
      gen.setFitness();
    }
  }

  private Genome createGen(){
    return new Genome(map, this);
  }

  private Genome[] crossover(Genome dad, Genome mum){
    Genome c1 = createGen();
    Genome c2 = createGen();
    Genome[] newtwo = {c1,c2};
    Genome[] oldtwo = {mum,dad};
    if(Math.random() > crossoverRate) return oldtwo;
    int length = dad.length;
    int beg = (int) (Math.random() * (length - 1));
    int end = beg+((int) (Math.random() * (length - beg - 1))) + 1;
    int tl = end-beg+1;
    int[][] pairs = new int[tl][2];
    int[] path1 = mum.getPath().clone();
    int[] path2 = dad.getPath().clone();
    for(int i=0;i<tl;i++){
      pairs[i][0] = path1[beg+i];
      pairs[i][1] = path2[beg+i];
    }
    for(int i=0;i<length;i++){
      for(int k=0;k<tl;k++){
        if(path1[i] == pairs[k][0]) {
          path1[i] = pairs[k][1];
        } else if(path1[i] == pairs[k][1]) {
          path1[i] = pairs[k][0];
        }
        if(path2[i] == pairs[k][0]) {
          path2[i] = pairs[k][1];
        } else if(path2[i] == pairs[k][1]) {
          path2[i] = pairs[k][0];
        }
      }
    }
    c1.setPath(path1);
    c2.setPath(path2);
    return newtwo;
  }


  private void mutate(){
    for(Genome g: gens){
      g.mutate(mutationRate);
    }
  }
}
