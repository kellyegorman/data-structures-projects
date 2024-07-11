import java.lang.Character;
import java.lang.String;
import java.lang.Integer;

/**
 * @author Kelly Gorman
 * class that employs methods from SuperDeque class
 */
public class QueueStackProblems {    
  
  /*
   * Returns the int answer from postfix String in argument
   * @param postfix  String of postfix equation
   * @return         int answer to postfix param
   */
  public static int evaluatePostFix(String postfix) {
    SuperDeque<Integer> valueStack = new SuperDeque<Integer>();
    //Loops through input string
    for(int i = 0; i < postfix.length(); i++) {
      char nextCharacter = postfix.charAt(i);
      int n = 0;
      if (Character.isDigit(nextCharacter)) {
        //Accounts for numbers with more than one digit
        while(Character.isDigit(nextCharacter)) {
          n = (n*10) + (nextCharacter - '0');
          i++;
          nextCharacter = postfix.charAt(i);
        }
        i--;
        valueStack.push(n);
      }
      //Handles elements in stack based on operations present
      if (nextCharacter != ' ') {
        int operandTwo = valueStack.pop();
        int operandOne = valueStack.pop();
        int total = 0;
        if (nextCharacter == '+') {
          total = operandOne + operandTwo;
        }
        else if (nextCharacter == '-') {
          total = operandOne - operandTwo;
        }
        else if (nextCharacter == '*') {
          total = operandOne * operandTwo;
        }
        else if (nextCharacter == '/') {
          total = operandOne / operandTwo;
        }
        else if (nextCharacter == '^') {
          total = operandOne ^ operandTwo;
        }
        valueStack.push(total);
      }
    }
    return valueStack.pop();
  }
  
  /*
   * Reverses letters of words in argument but does not change the 
   * order of the words, their spaces, or their punctuation
   * @param s  String with words/other characters to have letters reversed
   * @return   String that is reversed version of input string 
   */
  public static String reverseWords(String s) {
    SuperDeque<Character> letters = new SuperDeque<>();
    StringBuilder sb = new StringBuilder();
    //Reverse letters in each word 
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != ' ') {
        letters.push(s.charAt(i));
      }
      else {
        while (!letters.isEmpty()) {
          sb.append(letters.pop());
        }
        sb.append(" ");
      }
    }
    //Reverses letters in last word
    while (!letters.isEmpty()) {
      sb.append(letters.pop());
    }
    return sb.toString();
  }
  
  /*
   * Reverses elements in SuperDeque only until after k'th element, then 
   * adds elements in original order
   * @param dq   SuperDeque to have k elements reversed and added to new SD
   * @param k    Number of elements in dq to be reversed in new SD
   * @return     SuperDeque that contains k elements from dq in reverse order and 
   *             rest of the elements from dq in regular order
   */
  public static <E> SuperDeque<E> reverseK(SuperDeque<E> dq, int k) {
    SuperDeque<E> sd = new SuperDeque<E>();
    int nElements = dq.getNElements();
    //For cases where k is greater than the number of elements in dq
    if (k >= nElements) {
      nElements = k;
    }
    else {
      //Reverse the order of elements before k
        for(int i = 1; i < k; i++) {
          sd.push(dq.dequeue());
        }
        //Adds the elements after k in order
        while(!sd.isEmpty()) {
          dq.enqueue(sd.pop());
        }
      }
    return dq;
  }
  
  /*
   * Acts like a game where each iteration through numbers counts for one player's turn,
   * removes the player that offset counts to each time, winner is the last player not removed
   * @param n       Number of platers in game
   * @param offset  How many indexes counted each turn to determine who is removed
   * @return        int representing player that is the winner
   */
  public static int playGame(int n, int offset) {
    if(n <= 0) {
      return -1;
    }
    SuperDeque<Integer> sd = new SuperDeque<Integer>();
    //Creates the array of players
    for(int i = 1; i < n +1; i++) {
      sd.enqueue(i);
    }
    //Removes players one by one with setFront method and dequeue
    while(sd.getNElements() != 1) {
      sd.setFront((sd.getFront() + offset + sd.getLength())%sd.getNElements());
      sd.dequeue();
    }
    //used to check if my method was working correctly
    sd.print();
    //Move front indexforward by one because it ended being dequeued, currently null
    sd.setFront(sd.getFront() + 1);
    //Last element = player winner
    return sd.peek();
    
  }
  
}