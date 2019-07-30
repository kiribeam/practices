import  java.util.*;

public class PasswordUtils{
  @UseCase(description = "cntains enum", id = 47)
    public boolean validatePassword(String pass){
    return (pass.matches("\\w*\\d\\w*"));
  }
  @UseCase(id = 48)
    public String encryptPassword(String pw){
      return new StringBuilder(pw).reverse().toString();
    }
  @UseCase(id = 49, description = "new can't equal")
    public boolean checkForNewPassword(List<String> prevPasswords, String ps){
      return !prevPasswords.contains(ps);
    }
}
