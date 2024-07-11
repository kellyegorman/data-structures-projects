/**
 * @author Kelly Gorman
 * class that represents examples of recursive methods
 */
public class Recursion {
  
  /*
   * Adds and returns all digits of int in argument 
   * @param n integer input to have digits added
   * @return  int that represents all added digits
   */
  public int sumDigits(int n) {
    if(n < 10) {
      return n;
    }
    else {
      return ((n%10) + sumDigits(n/10));
    }
  }
  
  /*
   * Finds greatest common divisor of inputs and returns
   * @param a first integer considered for gcd
   * @param b second integer considered for gcd
   * @return  int that represents gcd between a and b
   */
  public int gcd(int a, int b) {
    if (b == a) {
      return b;
    }
    else if (a < b) {
      return gcd(a, b - a);
    }
    else {
      return gcd(a - b, b);
    }
  }
  
  /*
   * Checks if string in argument is a palindrome -- same letters, numbers, 
   * and spaces forward and backward.
   * @param str String to be checked for palindrome
   * @return    true if palindrome, false if not
   */
  public boolean isPalindrome(String str) {
    if(str.length() <= 1) {
      return true;
    }
    if (str.charAt(0) == str.charAt(str.length() - 1)) {
      return isPalindrome(str.substring(1, str.length() - 1));
    }
    return false;
  }
  
  /* 
   * Swaps nodes in pairs! First node swapped with second, 
   * third swapped with fourth, etc. 
   * 1, 2, 3, 4 --> 2, 1, 4, 3
   * @param head Node that represents the first node in list
   * @return     Node that represents new first node in list after swapping
   */
  public Node swapNodesInPairs(Node head) {
    if (head == null || head.getNext() == null) {
        return head;
    }
    Node nextPair = head.getNext().getNext();
    Node newStart = head.getNext();
    head.getNext().setNext(head);
    head.setNext(swapNodesInPairs(nextPair));
    return newStart;
}
  
  /*
   * Computes binomial based on equation:
   *   (n) =  (n!)
   *   (k)   (n-k)!k!
   * @param n int that represents n in above equation
   * @param k int that represents k in above equation
   * @return  int binomial that is output from above equation
   */
  public int binomial(int n, int k) throws IllegalArgumentException {
    if (n < 0 || k < 0) {
      throw new IllegalArgumentException();
    }
    if (k == 0 || k == n) {
      return 1;
    }
    return binomial(n - 1, k - 1) + binomial(n - 1, k);
  }

  
}