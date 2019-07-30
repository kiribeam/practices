import java.util.*;
import java.lang.Math.*;

public class CmapTsp{
  private ArrayList<City> cities = new ArrayList<>();
  final int citiesNumber;
  public CmapTsp(int n){
    if(n<1) n=8;
    citiesNumber = n;
    double x,y;
    for(int i=0;i<n;i++){
      //System.out.println(Math.PI);
      //System.out.println(Math.cos(Math.PI/4));
      x = Math.cos (2* i* Math.PI/n);
      //System.out.println(" x = " + x);
      y = Math.sin (2* i* Math.PI/n);
      cities.add(new City(x,y));
    }
  }
  public ArrayList<City> getCities(){
    return cities;
  }
}
