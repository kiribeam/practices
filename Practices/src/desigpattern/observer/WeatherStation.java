package desigpattern.observer;
import java.util.*;

public class WeatherStation implements Sender{
  private WeatherData weather;
  private ArrayList<Observer> observers = new ArrayList<>();

  public void setWeatherData(String s, double t){
    weather = new WeatherData(s,t);
    notifyObserver();
  }
  public void registObserver(Observer o){
    observers.add(o);
  }
  public void removeObserver(Observer o){
    observers.remove(o);
  }
  public void notifyObserver(){
    for(Observer o: observers){
      o.upDate(weather);
    }
  }
}
