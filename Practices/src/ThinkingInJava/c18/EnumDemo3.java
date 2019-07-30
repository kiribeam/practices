enum EnumDemo3{
  FIRST{
    @Override
    public String getInfo(){
      return "First";
    }
  },
  SECOND{
    @Override
    public String getInfo(){
      return "second";
    }
  };
  public abstract String getInfo();

  public static void main(String[] args){
    for(EnumDemo3 e: EnumDemo3.values())
      System.out.println(e.getInfo());
  }

}
