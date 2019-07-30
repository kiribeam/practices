import java.util.*;

public class Run{
  public static double totalDistance(Genome gen){
    ArrayList<City> cities = gen.map.getCities();
    int length = gen.length;
    //System.out.println("length is " + cities.size());
    double result = 0;
    for(int i=0;i<length;i++){
      //System.out.println(" i = " + i + "  , i+1 % length is "  + (i+1)%length);
      result += distanceOf(cities.get(gen.getPath()[i]),
          cities.get(gen.getPath()[(i+1)%length]));
    }
    //System.out.println("totalDistance is " + result);
    return result;
  }

  public static double distanceOf(City c1, City c2){
    double x = c1.x - c2.x;
    double y = c1.y - c2.y;
    x = x*x;
    y = y*y;
    return  500 * (Math.sqrt(x + y));
  }

  public static double fitnessOf(Genome gen){
    Group group = gen.group;
    double worstDistance = group.worstDistance;
    double fitness = worstDistance - gen.getDistance();
    //System.out.println(" fitness is " + fitness);
    return fitness;
  }
}
