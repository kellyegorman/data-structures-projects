public class Midterm233 {
  
  public static int sumOf(int n) {
    int sum;
    if (n == 1) {
      sum = 1;
    }
    else {
      sum = sumOf(n - 1) + n; 
    }
    return sum;
  }
  
}