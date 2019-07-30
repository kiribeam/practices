import java.util.*;

public class HWzip{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    go(input);
  }
  public static void go(String input){
    String tmp1="", tmp2="";
    int length = input.length();
    boolean flag = true;
    Node head = null;
    char c;
    for(int i=0; i<length; i++){
      if((c=input.charAt(i))>='a' && flag){
        tmp1+=c;
      }else if(!flag && c>='a'){
        Node node = new Node(tmp1, tmp2);
        head = insert(head, node);
        //syso(head);
        tmp1 = c+"";
        tmp2 = "";
        flag = true;
      }else{
        tmp2 += c;
        flag = false;
      }
    }
    head = insert(head, new Node(tmp1, tmp2));
    syso(head);
  }

  public static void syso(Node node){
    Node tmp = node;
    while(tmp != null){
      for(int i=0; i<tmp.times; i++){
        System.out.print(tmp.s);
      }
      tmp = tmp.next;
    }
  }

  public static Node insert(Node head, Node node){
    if(head == null) return node;
    Node tmp = head;
    while(tmp != null){
      if(tmp.isSmallerThan(node)){
        if(tmp.next == null){
          tmp.next = node;
          return head;
        }else if(tmp.next.isSmallerThan(node)){
          tmp=tmp.next;
          continue;
        }else{
          node.next = tmp.next;
          tmp.next = node;
          return head;
        }
      }else{
        node.next = head;
        return node;
      }
    }
    return head;
  }

  static class Node{
    int times;
    String s;
    Node next = null;

    public Node(String s1, String s2){
      s = s1;
      times = Integer.parseInt(s2);
    }

    public boolean isSmallerThan(Node node){
      if(this.times < node.times)
        return true;
      else if(this.times == node.times){
        int length = this.s.length();
        int size = node.s.length() < length ? node.s.length() : length;
        for(int i=0; i<size; i++){
          if(this.s.charAt(i) < node.s.charAt(i)) return true;
          else if(this.s.charAt(i) > node.s.charAt(i)) return false;
        }
        if(size < length) return false;
      }
      return false;
    }
  }
}
