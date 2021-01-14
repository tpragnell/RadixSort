import java.util.*;

public class Radix{

  public static void main(String[] args){
    System.out.println(nth(123,1));
    System.out.println(nth(-123,1));
    System.out.println(nth(123,2));
    System.out.println(nth(-123,2));
    System.out.println(nth(-123,8));

    System.out.println(length(0));
    System.out.println(length(15));
    System.out.println(length(-10));
    System.out.println(length(5112));
  }

  //get nth digit of an int, where 0 is the ones column, 1 is the tens column etc.
  public static int nth(int n, int col){
    String s = Integer.toString(Math.abs(n));
    if(col >= s.length()){
      return 0;
    }
    return Character.getNumericValue(s.charAt(s.length()-col-1));
  }

  //return the number of digits in n.
  public static int length(int n){
    String s = Integer.toString(Math.abs(n));
    return s.length();
  }
}
