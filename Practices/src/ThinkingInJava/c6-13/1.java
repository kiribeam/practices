class Homer{
  char doh(char c){
    System.out.println("Char and char");
    return 'x';
  }
}

class Bart extends Homer{
  @Override
  char doh(char a){
    System.out.println("Char and int");
    return 'c';
  }
}
