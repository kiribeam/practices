import java.util.*;

public class InD4{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    Node[] nodes = new Node[n];
    boolean[] reached = new boolean[n];
    reached[0] = true;
    int tmp1, tmp2, tmp3;
    Node tmpNode,curNode;
    for(int i=0; i<m; i++){
      tmp1 = sc.nextInt();
      tmp2 = sc.nextInt();
      tmp3 = sc.nextInt();
      tmpNode = new Node(tmp1-1, tmp2-1);
      curNode = nodes[tmp3-1];
      if(curNode == null) nodes[tmp3-1] = tmpNode;
      else {
        while(curNode.next != null) curNode=curNode.next;
        curNode.next = tmpNode;
      }
    }
    /*for(Node no: nodes){
      while(no != null){
        System.out.print(""+no.from + no.to + " ");
        no=no.next;
      }
      System.out.println("");
    }*/
    iter(0, reached, nodes);
    int result=1;
    int past = 0;
    while(past != result){
      past = result;
      result = 0;
      for(boolean b: reached)
        if(b) result ++;
    }
    System.out.println(result + "");
  }
  public static void iter(int current, boolean[] reached, Node[] nodes){
    Node node = nodes[current];
    if(node == null) return;
    Node before = null;
    if(reached[node.from]){
      if(reached[node.to]){
        nodes[current] = node.next;
        iter(current, reached, nodes);
        return;
      }
      reached[node.to] = true;
      nodes[current] = node.next;
      iter(node.to, reached, nodes);
    }

    while(node != null){
      if(!reached[node.from]){
        before = node;
        node = node.next;
      }
      else {
        if(reached[node.to]){
          if(before == null) {
            nodes[current] = node.next;
          }else
            before.next = node.next;
          node = node.next;
          continue;
        }
        reached[node.to] = true;
        if(before == null){
          nodes[current] = node.next;
        }else
          before.next = node.next;
        iter(node.to, reached, nodes);
      }
    }
  }
  public static class Node{
    public int from;
    public int to;
    public Node next = null;
    public Node(int from, int to){
      this.from = from;
      this.to = to;
    }
  }
}
