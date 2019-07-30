public class TestEnumState{
  public static State state = State.OK;
  public static boolean flag = false;
  enum State{
    OK{
      public void next(){
        state = State.BuOK;
      }
    },
    BuOK{
      public void next(){
        if(!flag) {
          flag = true;
        }else{
          flag = false;
          state = State.OK;
        }
      }
    };
    public abstract void next();
  }

  public static void main(String[] args){
    TestEnumState.state.next();
  }
}
