package desigpattern.observer;
public class WeatherPDA extends PDA{
  WeatherData weather;

  public void display(){
    if(weather == null){
      System.out.println("No data");
      return;
    }
    System.out.println(weather.weather);
  }
  public void upDate(WeatherData w){
    weather = w;
  }
}
