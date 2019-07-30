import java.util.ArrayList;
import java.util.*;

public class Qsort{
  public static int[] qSort1(int[] ints){
    int length = ints.length;
    ArrayList<Integer> array = new ArrayList<>();
    for(int i=0; i<length; i++ )
      array.add(ints[i]);
    array = qSort1(array);
    for(int i=0; i<length; i++)
      ints[i]=array.get(i);
    return ints;
  }
  public static int[] qSort2(int[] ints){
    int length = ints.length;
    ArrayList<Integer> array = new ArrayList<>();
    for(int i=0; i<length; i++ )
      array.add(ints[i]);
    array = qSort2(array);
    for(int i=0; i<length; i++)
      ints[i]=array.get(i);
    return ints;

  }
  public static ArrayList<Integer> qSort1(ArrayList<Integer> array){
    int size = array.size();
    if(size == 1 || size == 0) return array;
    ArrayList<Integer> arraySmall = new ArrayList<>();
    ArrayList<Integer> arrayBig = new ArrayList<>();
    int head = array.get(0);
    //System.out.println(head);
    for(int i=1; i<size; i++){
      int tmp = array.get(i);
      if(head > tmp)
        arraySmall.add(tmp);
      else
        arrayBig.add(tmp);
    }
    arraySmall = qSort1(arraySmall);
    arrayBig = qSort1(arrayBig);
    ArrayList<Integer> resultArray = new ArrayList<>();
    resultArray.addAll(arraySmall);
    resultArray.add(head);
    resultArray.addAll(arrayBig);
    return resultArray;
  }

  public static ArrayList<Integer> qSort2(ArrayList<Integer> array){
    int size = array.size();
    if(0 == size || 1 == size) return array;
    ArrayList<Integer> left = new ArrayList<>();
    ArrayList<Integer> right = new ArrayList<>();
    int half = size/2;
    for(int i=0; i<half; i++){
      left.add(array.get(i));
      array.remove(i);
    }
    right = array;
    ArrayList<Integer> result = new ArrayList<>();
    result = list(qSort2(left), qSort2(right));
    return result;
  }
  public static ArrayList<Integer> list(ArrayList<Integer> left,
      ArrayList<Integer> right){
    ArrayList<Integer> result = new ArrayList<>();
    if(left.size() == 0)
      result.addAll(right);
    else if(right.size() == 0) 
      result.addAll(left);
    else{
      if(left.get(0) < right.get(0)){
        result.add(left.get(0));
        left.remove(0);
        result.addAll(list(left, right));
      }
      else{
        result.add(right.get(0));
        right.remove(0);
        result.addAll(list(left,right));
      }
    }
    return result;
  }
  public static void main(String[] args){
    int[] array = {5, 4, 3, 2, 1};
    int[] array2 = {5, 4, 3, 2, 1};
    array = qSort1(array);
    array2 = qSort2(array2);
    System.out.println(Arrays.toString(array));
    System.out.println(Arrays.toString(array2));
  }
}
