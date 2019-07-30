//A TestDrive to test
public class TestDrive{
  public static void main(String[] args){
    int[][] m={{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
               {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1},
               {8, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
               {1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
               {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
               {1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
               {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
               {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5},
               {1, 0, 2, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
               {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    Map map = new Map(m);
    Gui g = new Gui(map);
    g.setGui();
    //group size is first arg 
    //crossover rate is .7
    //mutate rate is  .001
    //the size of chromosome is  fourth arg
    //It shows in serval tests that:
    //          Big group size can be effetive
    //          get a short path will take more time
    //          It can't be sure that get a result
    Group2 group = new Group2(100,0.7,0.001,24,map);
    System.out.println("The best Chromosome is (origin) :\n" +"   "  + group.bestChro());
    group.epoch();
    System.out.println("The best Chromosome is (1 generation) : \n "+  "     " + group.bestChro());
    while(group.bestChro().fitness < 1) group.epoch();
    Chromosome bestChro= group.bestChro();
    int[][] path = Run.convertPath(bestChro.getPath(),map);
    //System.out.println(path[0]);
    System.out.println("The best Chromosome that achieve is :\n"+ "    "+ bestChro);
    System.out.println("The generation used is" + group.getGeneration());
    System.out.println("The average of each generation is : \n"+ "    " +group.getFitPerGen());
    g.update(path); 
  }
}
