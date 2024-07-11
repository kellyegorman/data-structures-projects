import org.junit.*;
import static org.junit.Assert.*;
import java.lang.IndexOutOfBoundsException;

/**
 * @author Kelly Gorman
 * class that tests methods in SuperDeque and QueueStackProblems
 */
public class HomeworkTwoTester {
  
  //Field that represents a SuperDeque used to test all methods
  SuperDeque test = new SuperDeque();
  
  //Tests push method in SuperDeque
  @Test
  public void testPush() {
    //Empty deque
    assertEquals(0, test.getFront(), 0);
    assertEquals("nothing pushed yet", null, test.peek());
    test.push("one");
    //Pushed once, checks specific element
    assertEquals("'one' pushed", "one", test.peek());
    assertEquals(2, test.getLength(), 0);
    test.push("two");
    //Pushed twice, checks specific element
    assertEquals("'two' pushed", "two", test.peek());
    //Checks if length doubled again
    assertEquals(4, test.getLength(), 0);
    //3 should be index of front now because it was added to the "front"
    //(or went backwards on the circle) twice, going -1 -> -2, or 2(size 2) -> 3(size 4)
    assertEquals(3, test.getFront(), 0);
  }
  
  //Tests pop method in SuperDeque
  @Test
  public void testPop() {
    //Empty deque, nothing pushed, pop returns null
    assertEquals("nothing pushed, can't pop", null, test.pop());
    assertEquals(1, test.getLength(), 0);
    test.push("one");
    //Checks that pop returns element on top and deque is empty now
    assertEquals(2, test.getLength(), 0);
    assertEquals("pops 'one'", "one", test.pop());
    assertEquals("no elements can be peeked, null", null, test.peek());
    assertEquals("deque empty after only element popped", true, test.isEmpty());
    test.push("one");
    test.push("two");
    test.push("three");
    //pops element on top and in correct order, not all elements popped
    assertEquals(4, test.getLength(), 0);
    assertEquals("pop element 'on top'/most recently pushed", "three", test.pop());
    assertEquals("element accessed by peek", test.peek(), "two");
    assertEquals("pop next element 'on top', 'two'", "two", test.pop());
    assertEquals("element accessed by peek", test.peek(), "one");
    assertEquals("'one' wasn't popped, deque not empty", false, test.isEmpty());
    assertEquals("'one' only remaining element", "one", test.pop());
    assertEquals("no elements left in dq", true, test.isEmpty());
  }
  
  //Tests peek method in SuperDeque
  @Test
  public void testPeek() {
    //Empty deque, nothing pushed, peek returns null
    assertEquals("no elements in deque", null, test.peek());
    test.push("one");
    //Length doubled means it was correctly pushed
    assertEquals(2, test.getLength(), 0);
    //One element on list
    assertEquals("'one' element only on list", "one", test.peek());
    assertEquals("peek does not remove element", "one", test.peek());
    //Two elements in list 
    test.push("two");
    assertEquals("'two' is element at top of list now", "two", test.peek());
    test.pop();
    assertEquals("'one' on top of list", "one", test.peek());
  }
  
  //Tests enqueue method in SuperDeque
  @Test
  public void testEnqueue() {
    //Empty deque, nothing pushed, peek returns null
    assertEquals("no elements in deque", null, test.peek());
    //Add one element to deque using enqueue
    test.enqueue("one");
    assertEquals("'one' is only element on list", "one", test.peek());
    assertEquals(2, test.getLength(), 0);
    //Add another element to deque using enqueue should double size
    test.enqueue("two");
    assertEquals(4, test.getLength(), 0);
    assertEquals("'one' still at front, 'two' added to back, not accessed by peek", "one", test.peek());
    //Add another element to same deque using push (stack method)
    test.push("three");
    assertEquals("'three' most recent element on list", "three", test.peek());
  }
  
  //Tests dequeue method in SuperDeque
  @Test
  public void testDequeue(){
    //Empty deque, nothing pushed, peek returns null
    assertEquals("no elements in deque", null, test.peek());
    test.enqueue("one");
    test.enqueue("two");
    test.enqueue("three");
    assertEquals("'one' is at front", "one", test.peek());
    test.dequeue();
    assertEquals("'two' is at front", "two", test.peek());
    test.dequeue();
    assertEquals("'three' is at front", "three", test.peek());
    test.push("one");
    assertEquals("'one' is at front", "one", test.peek());
    test.dequeue();
    assertEquals("three at front", "three", test.peek());
    test.dequeue();
    assertEquals("no elements at front/in deque", true, test.isEmpty());
  }
  
  //Tests isEmpty method in SuperDeque
  @Test
  public void testIsEmpty() {
    assertEquals("no elements pushed or queued yet", null, test.peek());
    assertEquals("no elements added yet", true, test.isEmpty());
    test.push("one");
    assertEquals("one element added", false, test.isEmpty());
    test.push("two");
    assertEquals("two elements added", false, test.isEmpty());
    test.pop();
    assertEquals("one element removed, one in stack", false, test.isEmpty());
    test.pop();
    assertEquals("both elements removed, no elements in stack", true, test.isEmpty());
    test.enqueue("one");
    assertEquals("one element in queue", false, test.isEmpty());
    test.dequeue();
    assertEquals("no elements in queue", true, test.isEmpty());
  }
  
  //Tests doubleSize method in SuperDeque
  @Test
  public void testDoubleSize() {
    //one  null element in queue
    assertEquals(1, test.getLength(), 0);
    test.push("one");
    //element added, queue size doubles to accomodate so there is one null element always
    assertEquals(2, test.getLength(), 0);
    test.push("two");
    //element added, queue size double to accomodate
    assertEquals(4, test.getLength(), 0);
    test.push("three");
    //element added, does not double because there is still one null element
    assertEquals(4, test.getLength(), 0);
    test.push("four");
    //element added, doubles to accomodate
    assertEquals(8, test.getLength(), 0);
    test.pop();
    test.pop();
    test.pop();
    test.pop();
    //all elements popped but length stays the say, just all null elements now
    assertEquals(8, test.getLength(), 0);
  }
 
  //Tests toString method in SuperDeque
  @Test
  public void testToString() {
    assertEquals("No elements in SuperDeque yet", "", test.toString());
    test.push("one");
    assertEquals("One element in SD", "one", test.toString());
    test.push("two");
    assertEquals("Two elements in SD", "two, one", test.toString());
    test.push("three");
    assertEquals("Three elements in SD", "three, two, one", test.toString());
    test.pop();
    assertEquals("Two elements in SD", "two, one", test.toString());
    test.pop();
    test.pop();
    assertEquals("No elements in SuperDeque", "", test.toString());
  }
  
  //Tests evaluatePostFix method in QueueStackProblems
  @Test
  public void testEvaluatePostFix() {
    //Example given in method description
    assertEquals(53, QueueStackProblems.evaluatePostFix("27 3 9 * + 1 -"), 0);
    //Simple example to test the method
    assertEquals(4, QueueStackProblems.evaluatePostFix("1 1 1 1 + + +"), 0);
    //Same numbers and sign as previous problem, tests it can differentiate between
    //one and two digit numbers (1 1 vs. 11)
    assertEquals(22, QueueStackProblems.evaluatePostFix("11 11 +"), 0);
  }
  
  //Tests reverseWords method in QueueStackProblems
  @Test
  public void testReverseWords() {
    assertEquals("Example given in method description",
                 QueueStackProblems.reverseWords("This is a string. Hello World!"),
                 "sihT si a .gnirts olleH !dlroW");
    assertEquals("ex w/ upper and lower case and punctuation", 
                 QueueStackProblems.reverseWords("STRING !!! ExAmPlE two...!!!! two"),
                 "GNIRTS !!! ElPmAxE !!!!...owt owt");
    assertEquals("Exmpty string example", QueueStackProblems.reverseWords(""), "");
    assertEquals("one element string example", QueueStackProblems.reverseWords("a"), "a");
    assertEquals("one non-letter element string example", QueueStackProblems.reverseWords("1"), "1");
  }
  
  //Tests reverseK method in QueueStackProblems
  @Test
  public void testReverseK() {
    test.push("1"); test.push("2"); test.push("3"); test.push("4"); 
    test.push("5"); test.push("6"); test.push("7");
    assertEquals("Example given in method description", "4, 3, 2, 1, 5, 6, 7", 
                 QueueStackProblems.reverseK(test, 4).toString());
    SuperDeque emptyDeque = new SuperDeque();
    assertEquals("Empty superdeque doesn't cause an error", "", 
                 QueueStackProblems.reverseK(emptyDeque, 1).toString());
    SuperDeque test2 = new SuperDeque();
    test2.push("1"); test2.push("2"); test2.push("3"); test2.push("4"); 
    test2.push("5"); test2.push("6"); test2.push("7");
    assertEquals("Argument k greater than length", "7, 6, 5, 4, 3, 2, 1", 
                 QueueStackProblems.reverseK(test2, 8).toString());
    SuperDeque testOne = new SuperDeque();
    testOne.push("one");
    assertEquals("One element, exact length in argument", "one", 
                 QueueStackProblems.reverseK(testOne, 1).toString());
  }
  
  //Tests playGame method in QueueStackProblems
  @Test 
  public void testPlayGame() {
    //Example given in method description
    assertEquals(5, QueueStackProblems.playGame(6, 3), 0.0);
    //Element in argument <= 0
    assertEquals(-1, QueueStackProblems.playGame(0, 3), 0.0);
    //New game, winner should be 1 (2, 1, 2 eliminated)
    //Offset greater than number of players
    assertEquals(1, QueueStackProblems.playGame(2, 3), 0);
    //New game, winner should be 1 (2 eliminated)
    //Number of players greater than offset
    assertEquals(1, QueueStackProblems.playGame(2, 1), 0);
  }

}