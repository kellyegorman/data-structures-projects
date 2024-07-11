import org.junit.*;
import static org.junit.Assert.*;


/**
 * @author Kelly Gorman
 * class that tests methods in ArrayList, LinkedList and Recursion
 */
public class HomeworkThreeTester {
  
  //Field that represents an instance of the ArrayList class
  ArrayList test = new ArrayList();
  
  //Tests the add method in the ArrayList class
  @Test
  public void testAdd() {
    //Nothing added yet, length initialized to 1 (null)
    assertEquals(1, test.getLength(), 0);
    test.add(1);
    //One element added, makes element non-null
    assertEquals(1, test.getLength(), 0);
    test.add(2);
    //Another element added, doubles length
    assertEquals(2, test.getLength(), 0);
    //Test elements are added correctly
    assertEquals(1, test.getElement(0), 0);
    assertEquals(2, test.getElement(1), 0);
    test.add(3);
    //Length doubles again to 4, one null element
    assertEquals(4, test.getLength(), 0);
    assertEquals(3, test.getElement(2), 0);
    assertEquals("No element in index 3", test.getElement(3), null);
  }
  
  //Tests the second implementation of the add method in the ArrayList class
  @Test
  public void testAdd2() {
    //Nothing added yet, length initialized to 1 (null)
    assertEquals(1, test.getLength(), 0);
    //Add one element to list using previous add method, works correctly
    test.add(1);
    assertEquals(1, test.getElement(0), 0);
    //Add another element to list using new add method, works correctly
    test.add(2, 1);
    assertEquals(2, test.getElement(1), 0);
    //Add another element to list using new add method, works correctly
    test.add(1, 2);
    assertEquals(1, test.getElement(2), 0);
    //Add another element, index larger than length, adds to end
    test.add(5, 10);
    assertEquals(5, test.getElement(3), 0);
    test.add(10, 1);
    assertEquals(10, test.getElement(1), 0);
    assertEquals(2, test.getElement(2), 0);
  }
  
  //Tests the indexof method in the ArrayList class
  @Test
  public void testIndexOf() {
    test.add(0);
    test.add(2);
    test.add(50, 2);
    //0 is first element added, index 0
    assertEquals(0, test.indexof(0), 0);
    //2 was second element added, index 1
    assertEquals(1, test.indexof(2), 0);
    //50 was third element added (using other add method), index 0
    assertEquals(2, test.indexof(50), 0);
    //3 is not in list, return -1
    assertEquals(-1, test.indexof(3), 0);
  }
  
  //Tests the remove method in the ArrayList class
  @Test
  public void testRemove() {
    test.add(0);
    test.add(2);
    test.add(50, 2);
    //Remove method returns value at index removed
    assertEquals(test.remove(1), 2, 0);
    //Remove method shifts next element forward
    assertEquals(test.getElement(1), 50, 0);
    //Remove method does not alter other elements in array
    assertEquals(test.getElement(0), 0, 0);
    assertEquals("No element in index 2 now", test.getElement(2), null);
    //If remove index is greater than length, remove last element
    assertEquals(test.remove(5), 50, 0);
    assertEquals("Last element was removed", test.getElement(2), null);
  }
  
  //Tests the removeValue method in the ArrayList class
  @Test
  public void testRemoveVal() {
    test.add(0);
    test.add(2);
    test.add(50, 2);
    test.add(2);
    //Removes first (only) instance of 0 at index 0
    test.removeValue(0);
    //Shifts remaining elements forward
    assertEquals(test.getElement(0), 2, 0);
    assertEquals(test.getElement(1), 50, 0);
    //Removes first instance of 2 at index 1, shifts remaining elements forward
    test.removeValue(2);
    assertEquals(test.getElement(0), 50, 0);
    assertEquals(test.getElement(1), 2, 0);
    //Call again to remove second instance of 2
    test.removeValue(2);
    assertEquals("Second instance 2 was last element in list", test.getElement(2), null);
  }
  
  @Test
  public void testRemoveAll() {
    test.add(0);
    test.add(1);
    test.add(1);
    test.add(0);
    //Checks elements added correctly
    assertEquals(test.getElement(0), 0, 0);
    assertEquals(test.getElement(1), 1, 0);
    assertEquals(test.getElement(2), 1, 0);
    assertEquals(test.getElement(3), 0, 0);
    //Removes both 0's from list, list is now 2 1's
    test.removeall(0);
    assertEquals(test.getElement(0), 1, 0);
    assertEquals(test.getElement(1), 1, 0);
    //Removes both 1's from list, list is now empty
    test.removeall(1);
    assertEquals("Empty array!", test.getElement(0), null);
    ArrayList test3 = new ArrayList();
    test3.add(1); test3.add(2); test3.add(3); test3.add(4);
    test3.removeall(3);
    //Only one instance of 3, so only removes one element
    assertEquals(test3.getElement(2), 4, 0);
    
  }
  
  //Tests the mean method in the ArrayList class
  @Test
  public void testMean() {
    test.add(1); test.add(2); test.add(3);
    //mean should be 2 (1+2+3/3 = 2)
    assertEquals(2.0, test.mean(), 0);
    ArrayList mean2 = new ArrayList();
    mean2.add(1); mean2.add(57); mean2.add(2);
    //mean should be 20
    assertEquals(20.0, mean2.mean(), 0);
    ArrayList mean3 = new ArrayList();
    mean3.add(1);
    //mean should be 0 -- only 1 element
    assertEquals(1.0, mean3.mean(), 0);
    test.remove(2);
    //one element null & mean is not a whole number
    assertEquals(1.5, test.mean(), 0);
  }
  
  //Tests the variance method in the ArrayList class
  @Test
  public void testVariance() {
    ArrayList variance = new ArrayList();
    variance.add(1); variance.add(1); variance.add(1);
    //If all elements are the same/same as mean, variance = 0
    assertEquals(0.0, variance.variance(), 0);
    test.add(0); test.add(2); test.add(50, 2);
    //Calculate exact variance
    assertEquals(28.307831660749528, test.variance(), 0);
    ArrayList variance2 = new ArrayList();
    variance2.add(1); variance2.add(2); variance2.add(3);
    //(1^2 + 0^2 + 1^2)/(3-1) = 1
    assertEquals(1.0, variance2.variance(), 0);
    //One element is an outlier in test, larger variance() than variance2 instance
    assertEquals("Outlier leads to higher variance", test.variance() > variance2.variance(), true);
  }
  
  //Tests the sublist method in the ArrayList class
  @Test
  public void testSublist() {
    test.add(1); 
    test.add(3);
    test.add(47);
    test.add(7);
    test.add(4);
    //creates a sublist between these bounds and tests list is correct
    ArrayList sub = test.sublist(2, 7);
    assertEquals(sub.getElement(0), 3, 0);
    assertEquals(sub.getElement(1), 7, 1);
    assertEquals(sub.getElement(2), 4, 2);
    //creates a sublist that has no elements because nothing exists in this range
    ArrayList sub2 = test.sublist(100, 110);
    assertEquals("No elements within this range in test", sub2.getElement(0), null);
    //creates a sublit that has one element within range
    ArrayList sub3 = test.sublist(47, 47);
    assertEquals(sub3.getElement(0), 47, 0);
  }
  
  //Tests the removeNoise method in the ArrayList class
  @Test
  public void testRemoveNoise() {
    test.add(2); test.add(2); test.add(2);
    //All elements in this list should stay, 0 variance
    ArrayList test1 = test.removeNoise();
    assertEquals(test1.getLength(), 4, 0);
    assertEquals(test1.getElement(0), 2, 0);
    assertEquals(test1.getElement(1), 2, 0);
    assertEquals(test1.getElement(2), 2, 0);
    assertEquals("Nothing in fourth spot", test1.getElement(3), null);
    //Only two elements stay, other two excluded because they are not within 3 stdevs from mean
    ArrayList test3 = new ArrayList();
    test3.add(70); test3.add(120); test3.add(80); test3.add(1);
    ArrayList test4 = test3.removeNoise();
    assertEquals(test4.getLength(), 2, 0);
    assertEquals(test4.getElement(0), 70, 0);
    assertEquals(test4.getElement(1), 80, 0);
  }
  
  //Field that represents an instance of the LinkedList class
  LinkedList test2 = new LinkedList();
  
  //Tests the add method in the LinkedList class
  @Test
  public void testAddLL() {
    //List contains one null node
    assertEquals(test2.length(), 1, 0);
    assertEquals("Nothing added to first node", test2.firstElement(), null);
    test2.add(1);
    //element added to first node, still length 1
    assertEquals(test2.length(), 1, 0);
    assertEquals(1, test2.firstElement(), 0);
    //adds another element to new node at end of linkedlist, list length now 2
    test2.add(2);
    assertEquals(test2.length(), 2, 0);
    assertEquals(test2.getFirstNode().getNext().getElement(), 2, 0);
    //adds another element to new node at end of linkedlist, list length now 3
    test2.add(3);
    assertEquals(test2.length(), 3, 0);
    assertEquals(test2.getFirstNode().getNext().getNext().getElement(), 3, 0);
  }
  
  //Tests the second implementation of the add method in the LinkedList class
  @Test
  public void testAdd2LL() {
    //List contains one null node
    assertEquals(test2.length(), 1, 0);
    //Add first element using old add method
    test2.add(1);
    assertEquals(1, test2.firstElement(), 0);
    //Add second element using new add method -- can mix add methods in same LL
    test2.add(2, 1); 
    assertEquals(2, test2.getFirstNode().getNext().getElement(), 0);
    //index > length = add to new node at end
    test2.add(3, 10);
    assertEquals(3, test2.getFirstNode().getNext().getNext().getElement(), 0);
    //index < length = add at index
    test2.add(4, 1);
    assertEquals(test2.firstElement(), 1, 0);
    assertEquals(test2.getFirstNode().getNext().getElement(), 4, 0);
    //previous element at that index is now at (index + 1) 
    assertEquals(test2.getFirstNode().getNext().getNext().getElement(), 2, 0);
  }
  
  //Tests the indexOf method in the LinkedList class
  @Test
  public void testIndexOfLL() {
    test2.add(0);
    test2.add(2);
    test2.add(50, 2);
    //0 is first element added, index 0
    assertEquals(0, test2.indexof(0), 0);
    //2 was second element added, index 1
    assertEquals(1, test2.indexof(2), 0);
    //50 was third element added (using other add method), index 0
    assertEquals(2, test2.indexof(50), 0);
    //3 is not in list, return -1
    assertEquals(-1, test2.indexof(3), 0);
  }
  
  //Tests the  remove method in the LinkedList class
  @Test
  public void testRemoveLL() {
    test2.add(0);
    test2.add(2);
    test2.add(50, 2);
    test2.add(2);
    //Check before remove called
    assertEquals(test2.getFirstNode().getNext().getElement(), 2, 0);
    assertEquals(test2.getFirstNode().getNext().getNext().getElement(), 50, 0);
    test2.remove(1);
    //Changed after remove called
    assertEquals(test2.getFirstNode().getNext().getElement(), 50, 0);
    //Remove another element
    test2.remove(0);
    assertEquals(test2.firstElement(), 50, 0);
    //Only two left in list
    assertEquals(test2.length(), 2, 0);
  }
  
  //Tests the removeValue method in the LinkedList class
  @Test
  public void testRemoveValLL() {
    test2.add(0);
    test2.add(2);
    test2.add(50, 2);
    test2.add(2);
    //Remove first instance of 2 but not the second
    test2.removeValue(2);
    assertEquals(test2.getFirstNode().getNext().getElement(), 50, 0);
    assertEquals(test2.getFirstNode().getNext().getNext().getElement(), 2, 0);
    assertEquals(test2.length(), 3, 0);
    //Remove second instance of 2 
    test2.removeValue(2);
    assertEquals(test2.length(), 2, 0);
    assertEquals(test2.firstElement(), 0, 0);
    assertEquals(test2.getFirstNode().getNext().getElement(), 50, 0);
  }
  
  @Test
  public void testRemoveAllLL() {
    test2.add(0);
    test2.add(1);
    test2.add(1);
    test2.add(0);
    //Checks elements added correctly
    assertEquals(test2.firstElement(), 0, 0);
    assertEquals(test2.getFirstNode().getNext().getElement(), 1, 0);
    assertEquals(test2.getFirstNode().getNext().getNext().getElement(), 1, 0);
    assertEquals(test2.getFirstNode().getNext().getNext().getNext().getElement(), 0, 0);
    //Removes both 0's from list, list is now 2 1's
    test2.removeall(0);
    assertEquals(test2.firstElement(), 1, 0);
    assertEquals(test2.getFirstNode().getNext().getElement(), 1, 0);
    assertEquals(test2.length(), 2, 0);
    //Removes both 1's from list, list is now empty
    test2.removeall(1);
    assertEquals(test2.length(), 0, 0);
    LinkedList test3 = new LinkedList();
    test3.add(1); test3.add(2); test3.add(3); test3.add(4);
    test3.removeall(3);
    //Only one instance of 3, so only removes one element
    assertEquals(test3.getFirstNode().getNext().getNext().getElement(), 4, 0);
  }
  
  //Tests the mean method in the LinkedList class
  @Test
  public void testMeanLL() {
    //All elements are the same, mean = all elements
    test2.add(1);
    test2.add(1);
    test2.add(1);
    test2.add(1);
    assertEquals(1, test2.mean(), 0);
    //Mean from a variety of numbers
    LinkedList l = new LinkedList();
    l.add(1);
    l.add(2);
    l.add(3);
    assertEquals(2, l.mean(), 0);
    //Mean is non-whole number, variety of add methods
    LinkedList l2 = new LinkedList();
    l2.add(0);
    l2.add(2);
    l2.add(50, 2);
    l2.add(2);
    assertEquals(13.5, l2.mean(), 0);
  }
  
  //Tests the variance method in the LinkedList class
  @Test
  public void testVarianceLL() {
    //Elements all equal, no variance
    test2.add(1); test2.add(1); test2.add(1);
    assertEquals(0, test2.variance(), 0);
    //First and last element difference is 1, middle element diff 0
    //total variance = 0
    LinkedList l = new LinkedList();
    l.add(1); l.add(2); l.add(3);
    assertEquals(1, l.variance(), 0);
    //Variance must be calculated, not whole number
    LinkedList l2 = new LinkedList();
    l2.add(1); l2.add(2); l2.add(4);
    assertEquals(1.3743685418725538, l2.variance(), 0);
  }
  
  //Tests the sublist method in the LinkedList class
  @Test
  public void testSublistLL() {
    test2.add(1); 
    test2.add(3);
    test2.add(47);
    test2.add(7);
    test2.add(4);
    //creates a sublist between these bounds and tests list is correct
    LinkedList sub = test2.sublist(2, 7);
    assertEquals(sub.firstElement(), 3, 0);
    assertEquals(sub.getFirstNode().getNext().getElement(), 7, 1);
    assertEquals(sub.getFirstNode().getNext().getNext().getElement(), 4, 2);
    //creates a sublist that has no elements because nothing exists in this range
    LinkedList sub2 = test2.sublist(100, 110);
    assertEquals("No elements within this range in test", sub2.firstElement(), null);
    //creates a sublit that has one element within range
    LinkedList sub3 = test2.sublist(47, 47);
    assertEquals(sub3.firstElement(), 47, 0);
  }
  
  //Tests the removeNoise method in the LinkedList class
  @Test
  public void testRemoveNoiseLL() {
    test2.add(2); test2.add(2); test2.add(2);
    //All elements in this list should stay, 0 variance
    LinkedList test3 = test2.removeNoise();
    assertEquals(test3.length(), 3, 0);
    assertEquals(test3.firstElement(), 2, 0);
    assertEquals(test3.getFirstNode().getNext().getElement(), 2, 0);
    assertEquals(test3.getFirstNode().getNext().getNext().getElement(), 2, 0);
    //Only two elements stay, other two excluded because they are not within 3 stdevs from mean
    LinkedList test4 = new LinkedList();
    test4.add(70); test4.add(120); test4.add(80); test4.add(1);
    LinkedList test5 = test4.removeNoise();
    assertEquals(test5.length(), 2, 0);
    assertEquals(test5.firstElement(), 70, 0);
    assertEquals(test5.getFirstNode().getNext().getElement(), 80, 0);
  }
  
  //Field that represents an instance of the Recursion class
  Recursion r = new Recursion();
  
  //Tests the sumDigits method in the Recursion class
  @Test
  public void testSumDigits() {
    //one digits, answer is input
    assertEquals(r.sumDigits(1), 1, 0);
    //two digits, 1+0=1
    assertEquals(r.sumDigits(10), 1, 0);
    //two digits, one nonzero 1+1=2
    assertEquals(r.sumDigits(11), 2, 0);
    //zero, answer is zero
    assertEquals(r.sumDigits(0), 0, 0);
    //four digits, 1+2+3+4=10
    assertEquals(r.sumDigits(1234), 10, 0);
  }
  
  //Tests the gcd method in the Recursion class
  @Test
  public void testGCD() {
    //5/5 = 1, 15/5 = 3, 5 gcd
    assertEquals(r.gcd(5, 15), 5, 0);
    //inputs equals, gcd is either input
    assertEquals(r.gcd(10, 10), 10, 0);
    //126/18=7, 54/18=3, gcd is 18
    assertEquals(r.gcd(54, 126), 18, 0);
    //88/2=44 6/2=3, gcd is 2
    assertEquals(r.gcd(6, 88), 2, 0);
    //52 and 7 have no gcd other than 1
    assertEquals(r.gcd(7, 52), 1, 0);
    //0 and 0, gcd 0
    assertEquals(r.gcd(0, 0), 0, 0);
  }
  
  //Tests the isPalindrome method in the Recursion class
  @Test
  public void testIsPalindrome() {
    //Tests cases given in Strings in assertEquals methods
    assertEquals("All same letter", r.isPalindrome("aaaaaaa"), true);
    assertEquals("One letter", r.isPalindrome("a"), true);
    assertEquals("No letters", r.isPalindrome(""), true);
    assertEquals("Not a palindrome", r.isPalindrome("Kelly"), false);
    assertEquals("Includes numbers", r.isPalindrome("1aaa1"), true);
    assertEquals("Includes spaces", r.isPalindrome("I racecar I"), true);
  }
  
  //Tests the swapNodes method in the Recursion class
  @Test
  public void testSwapNodes() {
    //Four elements, order should go from 1->2->3->4 to 2->1->4->3
    Node pointer = new Node(1, new Node(2, new Node(3, new Node(4, null))));
    Node point2 = r.swapNodesInPairs(pointer);
    assertEquals(point2.getElement(), 2, 0);
    assertEquals(point2.getNext().getElement(),1, 0);
    assertEquals(point2.getNext().getNext().getElement(), 4, 0);
    assertEquals(point2.getNext().getNext().getNext().getElement(), 3, 0);
    //Three elements, order should go from 1->2->3 to 2->1->3
    Node p = new Node(1, new Node(2, new Node(3, null)));
    Node p2 = r.swapNodesInPairs(p);
    assertEquals(p2.getElement(), 2, 0);
    assertEquals(p2.getNext().getElement(), 1, 0);
    assertEquals(p2.getNext().getNext().getElement(), 3, 0);
    //One element, order doesn't change
    Node p3 = new Node(1, null);
    Node p4 = r.swapNodesInPairs(p3);
    assertEquals(p4.getElement(), 1, 0);
    assertEquals("Only one element still", p4.getNext(), null);
  }
  
  //Tests the binomial method in the Recursion class
  @Test
  public void testBinomial() {
    //4! = 24, (4-3)! = 1, 3! = 6, 24/(6*1) = 4
    assertEquals(r.binomial(4,3), 4, 0);
    //1/1*1 = 1
    assertEquals(r.binomial(1,1), 1, 0);
    //2! = 2, 2/2*1 = 1
    assertEquals(r.binomial(2,2), 1, 0);
    //2! = 2, 2/1*1 = 2
    assertEquals(r.binomial(2, 1), 2, 0);
  }

}