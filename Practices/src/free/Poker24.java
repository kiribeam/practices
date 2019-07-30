import java.util.*;
public class Poker24{
  public int count = 0;
  public int[][] store;
  public static void main(String[] args){
    Poker24 p = new Poker24();
    int[] array = {1, 3, 2, 4};
    p.resolve(array);
  }
  public void resolve(int[] facs){
    arrange(facs);
    String s;
    for(int[] fac : store){
      int temp = 0;
      for(int i=0; i<4; i++){
        for(int j=0; j<4; j++){
          for(int k=0; k<4; k++){
            boolean flag1= false, flag2 =false;
            temp = 0;
            try{
              temp = cal(fac[0], fac[1], i);
              temp = cal(temp, fac[2], j);
              temp = cal(temp, fac[3], k);
            }catch(Exception e){
              flag1=true;
            }
            if(temp == 24 && !flag1){
              System.out.println("((" + fac[0] + getOp(i) + fac[1] + ") " + getOp(j) + fac[2] + ")" + getOp(k) + fac[3]);
            }
            try{
              temp = cal(fac[0], fac[1], i);
              int temp2 = cal(fac[2], fac[3], k);
              temp = cal(temp, temp2, j);
            }catch(Exception e){
              flag2 = true;
            }
            if(temp == 24 && !flag2){
              System.out.println("(" + fac[0] + getOp(i) + fac[1] +  ")" + getOp(j) + "(" + fac[2] + getOp(k) + fac[3] + ")");
            }
          }
        }
      }
    }
  }
  public int cal(int a, int b, int op)throws Exception{
    if(op==0) return a+b;
    if(op==1){
     if(a-b<0)throw new Exception();
     return a-b;
    }
    if(op==2) return a*b;
    if(a%b!=0) throw new Exception();
    return a/b;
  }
  
  public String getOp(int i){
    if(i==0) return "+";
    if(i==1) return "-";
    if(i==2) return "*";
    return "/";
  }


  public void arrange(int[] facs){
    int length = facs.length;
    store = new int[factor(length, 1)][length];
    iter(facs, 0);
    System.out.println(Arrays.toString(store[2]));
  }

  public int factor(int i, int result){
    if(i==1) return result;
    return factor(i-1, result*i);
  }
  public void swap(int[] array, int i, int j){
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
  public boolean doSwap(int[] array, int index){
    for(int i=index+1; i<array.length; i++){
      if(array[index] == array[i]) return false;
    }
    return true;
  }
  public void iter(int[] array, int index){
    int length = array.length;
    if(index == length-1){
      synchronized(this){
        store[count] = array.clone();
        count++;
      }
      return;
    }
    for(int i=index; i<length; i++){
      if(doSwap(array, index)){
        swap(array, index, i);
        iter(array, index+1);
        swap(array, index, i);
      }
    }
  }
}
