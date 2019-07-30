package decidetree;

import java.util.ArrayList;
public class DecideTree{
  public DecideTreeNode rootNode;
  public DecideTree(Status status){
    rootNode = new DecideTreeNode(0,status);
  }
  public int getResult(){
    return rootNode.result;
  }
}
class DecideTreeNode{
  public final int level;
  public Status status;
  public ArrayList<DecideTreeNode> leaves;
  public int result;
  public DecideTreeNode(int level, Status status){
    this.level = level;
    this.status = status;
    if(! status.over()){
      leaves = new ArrayList<>();
      ArrayList<Status> childStatus = status.operate();
      for(Status s: childStatus){
        DecideTreeNode d = new DecideTreeNode(level + 1, s);
        leaves.add(d);
      }
      this.result = calculateResult(level, leaves);
    }else{
      if(level%2 == 0) this.result = 1;
      else this.result = -1;
    }
  }
  private int calculateResult(int level, 
      ArrayList<DecideTreeNode> list){
    if(0 == level%2)
      return max(list);
    else
      return min(list);
  }
  private int max(ArrayList<DecideTreeNode> list){
    int i = list.get(0).result;
    for(DecideTreeNode d: list){
      if(i<d.result)
        i = d.result;
    }
    return i;
  }

  private int min(ArrayList<DecideTreeNode> list){
    int i = list.get(0).result;
    for(DecideTreeNode d: list)
      if(i> d.result)
        i = d.result;
    return i;
  }
}
