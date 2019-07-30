package desigpattern.observer;
public class WeatherTest{
  public static void main(String[] args){
    WeatherStation station = new WeatherStation();
    PDA p1 = new WeatherPDA();
    p1.display();
    station.registObserver(p1);
    station.setWeatherData("haru", 30.0);
    p1.display();
    station.setWeatherData("KusoAtsu", 50);
    p1.display();
  }
}
