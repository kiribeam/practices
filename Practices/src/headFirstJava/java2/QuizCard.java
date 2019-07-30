public class QuizCard {
  private String question = null;
  private String answer = null;
  public QuizCard(String q, String a){
    question = q;
    answer = a;
  }
  public String getQuestion() {
    return question;
  }
  public String getAnswer(){
    return answer;
  }
}
