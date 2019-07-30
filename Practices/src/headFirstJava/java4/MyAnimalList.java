public class MyAnimalList{

  private Animal[] animals = new Animal[5];
  private int nextIndex = 0;

  public void add(Animal a) {
    if(nextIndex < animals.length) {
      animals[netIndex] = a;
      System.out.println("Anima added at  " + nexIndex );
      nextIndex++;
    }
  }
}
