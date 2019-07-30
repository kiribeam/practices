public enum RoShamBo2 implements Competitor<RoShamBo2>{
  PAPER(Outcome.DRAW, Outcome.LOSE, Outcome.WIN),
  SCISSORS(Outcome.WIN, Outcome.DRAW, Outcome.LOSE),
  ROCK(Outcome.LOSE, Outcome.WIN, Outcome.DRAW);

  private Outcome vPaper, vScissors, vRock;

  RoShamBo2(Outcome p, Outcome s, Outcome r){
    vPaper = p;
    vScissors = s;
    vRock = r;
  }

  public Outcome compete(RoShamBo2 it){
    switch(it){
      default:
      case PAPER : return vPaper;
      case SCISSORS: return vScissors;
      case ROCK: return vRock;
    }
  }

  public static void main(String[] args){

  }
}

