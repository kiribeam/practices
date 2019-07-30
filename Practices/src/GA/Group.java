import java.util.*;

public class Group{
  private static int generation = 0;
  private static ArrayList<Double> fitPerGen;
  private ArrayList<Chromosome> chros = new ArrayList<>();
  final int size;
  final double crossRate;
  final double mutationRate;
  final int chroSize;
  final Map map;
  public Group(int s, double c, double m, int cs, Map map){
    size = s;
    crossRate = c;
    mutationRate = m;
    chroSize = cs;
    this.map = map;
    Chromosome cTemp;
    fitPerGen = new ArrayList<>();
    for(int i=0; i<size; i++){
      cTemp = createChromosome();
      Run.run(cTemp);
      chros.add(cTemp);
    }
    fitPerGen.add(bestChro().fitness);
  }
  public Chromosome createChromosome(){
    return new Chromosome(chroSize, mutationRate, map);
  }
  public void epoch(){
    ArrayList<Chromosome> newChros = new ArrayList<>();
    int babySize = 0;
    for(;babySize<size; babySize+=2){
      Chromosome mum = rouletteWheel();
      Chromosome dad = rouletteWheel();
      Chromosome[] babys = crossOver(mum,dad);
      babys[0].mutate();
      babys[1].mutate();
      newChros.add(babys[0]);
      newChros.add(babys[1]);
    }
    generation ++;
    chros = newChros;
    for(Chromosome c: chros){
      Run.run(c);
    }
    fitPerGen.add(aveFitness());
  }
  public Chromosome rouletteWheel(){
    double totalFit = aveFitness() * size;
    totalFit *= Math.random();
    double fit = 0;
    Chromosome cTemp = chros.get(size-1);
    for(Chromosome c: chros){
      fit += c.fitness;
      if(fit> totalFit) return c;
    }
    return cTemp;
  }
  public Chromosome[] crossOver(Chromosome mum, Chromosome dad){
    Chromosome[] babies = new Chromosome[2];
    if(Math.random() > crossRate){
      babies[0] = mum;
      babies[1] = dad;
    }
    else {
      ArrayList<Integer> a1 = new ArrayList<>();
      ArrayList<Integer> a2 = new ArrayList<>();
      int cut = (int) (Math.random() * chroSize);
      for(int i=0;i<cut; i++){
        a1.add(mum.getList().get(i));
        a2.add(dad.getList().get(i));
      }
      for(int i=cut; i<chroSize; i++){
        a1.add(dad.getList().get(i));
        a2.add(mum.getList().get(i));
      }
      babies[0] = createChromosome();
      babies[1] = createChromosome();
      babies[0].setList(a1);
      babies[1].setList(a2);
    }
    return babies;
  }
  public double aveFitness(){
    double totalFit = 0;
    for(Chromosome c: chros){
      totalFit += c.fitness;
    }
    return totalFit/size;
  }
  public int getGeneration(){
    return generation;
  }
  public ArrayList<Double> getFitPerGen(){
    return fitPerGen;
  }
  public Chromosome bestChro(){
    Chromosome cTemp = chros.get(0);
    double fit = 0;
    int length = chroSize;
    for(Chromosome c: chros){
      if(c.fitness > fit) {
        fit = c.fitness;
        cTemp = c;
        length = c.getPath().size();
      }else if(c.fitness == fit && c.getPath().size()<length){
        cTemp = c;
        length =  c.getPath().size();
      }
    }
    return cTemp;
  }
}
