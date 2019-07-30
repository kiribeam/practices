package desigpattern.observer;
public interface Sender{
  public void registObserver(Observer o);
  public void removeObserver(Observer o);
  public void notifyObserver();
}
