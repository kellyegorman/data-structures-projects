import org.junit.*;
import static org.junit.Assert.*;
import java.lang.IndexOutOfBoundsException;

/* 
 * A class to test the methods in DemoBag and DualImplementationBag
 */
public class DIBTester {
  
  //field that represents a bag that stores an ArrayList
  DualImplementationBag<String> dibTrue = new DualImplementationBag<String>(true);
  
  //field that represents a bag that stores a LinkedList
  DualImplementationBag<String> dibFalse = new DualImplementationBag<String>(false);
  
  //field that stores a DemoBag to test DB methods
  DemoBag dbTest = new DemoBag();
  
  /*
   * Method to test useArrayList method in DualImplementationBag class
   */
  @Test
  public void testSetUseArrayList() {
    dibTrue.add("one");
    dibFalse.add("one");
    //dibTrue's interal structure is ArrayList, getUseArray is true
    assertEquals("dibTrue is AL", true, dibTrue.getUseArrayList());
    //dibFalse's internal structure is LinkedList, getUseArrayList is false
    assertEquals("dibFalse is LL", false, dibFalse.getUseArrayList());
    dibTrue.setUseArrayList(false);
    //dibTrue's internal structure is now LinkedList, getUseArrayFalse
    assertEquals("dibTrue is AL now", false, dibTrue.getUseArrayList());
    //dibTrue and dibFalse now both have LinkedList internal structure, internal structures match
    assertEquals("both false now", dibTrue.getUseArrayList(), dibFalse.getUseArrayList());
    //dibTrue still has elements retained from when it was ArrayList
    assertEquals("elements retained after changed from AL to LL", dibTrue.get(0), "one");
    dibFalse.add("two");
    dibFalse.setUseArrayList(true);
    //dibFalse retains multiple elements after changing internal structure to ArrayList 
    assertEquals("elements retained after switch to AL in bag containing multiple elements", dibFalse.get(1), "two");
  }
  
  /*
   * Method to test add method in DualImplementationBag class
   */
  @Test
  public void testAdd() {
    //dibFalse has nothing in bag yet, size is zero
    assertEquals(dibTrue.size(), 0, 0);
    dibTrue.add("one");
    //dibTrue has one element added to bag, size is one and element matches what
    //was specified with add method
    assertEquals("first element string", "one", dibTrue.get(0));
    assertEquals(dibTrue.size(), 1, 0);
    dibTrue.add("two");
    //dibTrue has multiple elements to bag, element and size match after method call
    assertEquals("second element string", "two", dibTrue.get(1));
    assertEquals(dibTrue.size(), 2, 0);
    dibFalse.add("one");
    //add method also works for bags with LinkedList internal structure
    assertEquals("first element string", "one", dibFalse.get(0));
    dibFalse.add("two");
    //add method also works for multiple elements in LinkedList internal structure
    assertEquals("second element string", "two", dibFalse.get(1));
  }
  
  /*
   * Method to test remove method in DualImplementationBag class
   */
  @Test 
  public void testRemove() {
    dibTrue.add("one");
    dibTrue.add("two");
    dibTrue.add("three");
    //dibTrue bag size is 3 after adding three elements
    assertEquals(dibTrue.size(), 3, 0);
    dibTrue.remove("three");
    //dibTrue bag size is 2 after removing one element
    assertEquals(dibTrue.size(), 2, 0);
    dibTrue.remove("one");
    //dibTrue bag size is one after removing another elemenetg
    assertEquals(dibTrue.size(), 1, 0);
    //element remaining in bag is the only one not specifically removed, now
    //occupies the first index, which it did not originally
    assertEquals("'two' only element remaining", dibTrue.get(0), "two");
  }
  
  /*
   * Method to test contains method in DualImplementationBag class
   */
  @Test 
  public void testContains() {
    dibTrue.add("one");
    dibTrue.add("two");
    //checks if elements previously added can be found in list using contains method
    assertEquals("list includes this element", true, dibTrue.contains("one"));
    assertEquals("list includes this element", true, dibTrue.contains("two"));
    //checks if element not previously added cannot be found in list
    assertEquals("list doesn't include element", false, dibTrue.contains("three"));
    //checks if empty bag contains specific element
    assertEquals("different list, doesn't include element", false, dibFalse.contains("one"));
    dibTrue.remove("two");
    //checks that contains method does not return true for elements previously on list and then removed
    assertEquals("list doesn't include element anymore", false, dibTrue.contains("two"));
  }
  
  /*
   * Method to test isEmpty method in DualImplementationBag class
   */
  @Test
  public void testIsEmpty() {
    //dibTrue bag has nothing in it, is empty
    assertEquals("no elements added", true, dibTrue.isEmpty());
    dibTrue.add("one");
    //dibTrue bag has one element, is not empty
    assertEquals("one element added", false, dibTrue.isEmpty());
    dibTrue.add("two");
    dibTrue.remove("one");
    //dibTrue bag has one element added and first element removed, still not empty
    assertEquals("element removed, not empty", false, dibTrue.isEmpty());
    dibTrue.remove("two");
    //dibTrue bag previously had one element and then that element was removed, 
    //empty again
    assertEquals("element removed, empty", true, dibTrue.isEmpty());
    //dibFalse bag never had elements added or removed while dibTrue did, still empty
    assertEquals("no elements ever added or removed", true, dibFalse.isEmpty());
  }
  
  /*
   * Method to test size method in DualImplementationBag class
   */
  @Test
  public void testSize() {
    //No elements added, size 0
    assertEquals(dibTrue.size(), 0, 0);
    //add one element, size 1
    dibTrue.add("one");
    assertEquals(dibTrue.size(), 1, 0);
    //add another element, size 2
    dibTrue.add("two");
    assertEquals(dibTrue.size(), 2, 0);
    //remove an element, size 1
    dibTrue.remove("one");
    assertEquals(dibTrue.size(), 1, 0);
    //remove another element (size 0) and test if same size as empty bag (size 0)
    dibTrue.remove("two");
    assertEquals("bags are now both size 0", dibTrue.size(), dibFalse.size());
  }
  
  /*
   * Method to test getFrequency method in DualImplementationBag class
   */
  @Test
  public void testGetFrequencyOf() {
    //empty list, no elements
    assertEquals(0, dibTrue.getFrequencyOf("one"), 0);
    dibTrue.add("one");
    //different element
    assertEquals(0, dibTrue.getFrequencyOf("two"), 0);
    //one occurrence 
    assertEquals(1, dibTrue.getFrequencyOf("one"), 0);
    dibTrue.add("one");
    dibTrue.add("two");
    //no occurrences
    assertEquals(0, dibTrue.getFrequencyOf("three"), 0);
    //one occurrence
    assertEquals(1, dibTrue.getFrequencyOf("two"), 0);
    //two occurrences
    assertEquals(2, dibTrue.getFrequencyOf("one"), 0);
  }
  
  /*
   * Method to test get method in DualImplementationBag class
   */
  @Test
  public void testGet() {
    dibTrue.add("one");
    //Checks if element found in 0 index is element just added
    assertEquals("first element, index 0", "one", dibTrue.get(0));
    dibTrue.add("two");
    //Checks if element found in 0 index is still element added first
    assertEquals("still index 0, even after adding another element", "one", dibTrue.get(0));
    //Checks if element found in 1 index is element added second
    assertEquals("index 1, added another element", "two", dibTrue.get(1));
    dibFalse.add("ngksjerhgiushGEIFeritoAFauhwerio");
    //Checks that get method still works for longer, random element 
    assertEquals("works in a different bag with a random string", "ngksjerhgiushGEIFeritoAFauhwerio", dibFalse.get(0));
  }
  
  /*
   * Method to test removeAll method in DemoBag class
   */
  @Test
  public void testRemoveAll() {
    dibTrue.add("one");
    dibTrue.add("two");
    //see if element is on list before removal
    assertEquals(1, dibTrue.getFrequencyOf("one"), 0); 
    dbTest.removeAll(dibTrue, "one");
    //see if element was removed
    assertEquals(0, dibTrue.getFrequencyOf("one"), 0);
    //see if elements not specfied were not removed
    assertEquals(1, dibTrue.getFrequencyOf("two"), 0);
    dibTrue.add("three");
    dibTrue.add("three");
    dibTrue.add("three");
    //see if three instance of element are on list
    assertEquals(3, dibTrue.getFrequencyOf("three"), 0);
    dbTest.removeAll(dibTrue, "three");
    //see if elements were all removed
    assertEquals(0, dibTrue.getFrequencyOf("three"), 0);
    //see if elements not specified were not removed
    assertEquals(1, dibTrue.getFrequencyOf("two"), 0);
  }
  
  /*
   * Method to test retainAll method in DemoBag class
   */
  @Test
  public void testRetainAll() {
    dibTrue.add("one");
    dibTrue.add("two");
    dibTrue.add("three");
    dibTrue.add("one");
    dibTrue.add("one");
    //size and elements before removing
    assertEquals(5, dibTrue.size(), 0);
    //checks specific element that is present before retaining
    assertEquals("second element in dibTrue before retaining", "two", dibTrue.get(1));
    //size and elements after removing
    dbTest.retainAll(dibTrue, "one");
    assertEquals("second element after retainAll called", "one", dibTrue.get(1));
    assertEquals("first element after retainAll called", "one", dibTrue.get(0));
    assertEquals(3, dibTrue.size(), 0);
  }
  
  /*
   * Method to test union method in DemoBag class
   */
  @Test
  public void testUnion() {
    DualImplementationBag<String> bagUnion = new DualImplementationBag<String>(true);
    dibTrue.add("one");
    dibTrue.add("two");
    dibFalse.add("three");
    dibFalse.add("four");
    bagUnion = dbTest.union(dibTrue, dibFalse);
    //Checks size of union -- should be four because two elements in dibTrue and two in dibFalse
    assertEquals(4, bagUnion.size(), 0);
    //Checks each element in union to see if they match dibTrue and dibFalse and correct order
    assertEquals("first element in union - from dibTrue", "one", bagUnion.get(0));
    assertEquals("second element in union - from dibTrue", "two", bagUnion.get(1));
    assertEquals("third element in union - from dibFalse", "three", bagUnion.get(2));
    assertEquals("fourth element in union - from dibFalse", "four", bagUnion.get(3));
    bagUnion.remove("three");
    assertEquals("bagUnion acts like regular bag, remove method still works", "four", bagUnion.get(2));
  }
  
  /*
   * Method to test intersection method in DemoBag class
   */
  @Test
  public void testIntersection() {
    DualImplementationBag<String> bagIntersect = new DualImplementationBag<String>(true);
    DualImplementationBag<String> bagIntersectTwo = new DualImplementationBag<String>(true);
    dibTrue.add("one");
    dibTrue.add("two");
    dibTrue.add("three");
    dibTrue.add("four");
    dibFalse.add("three");
    dibFalse.add("four");
    dibFalse.add("five");
    dibFalse.add("six");
    bagIntersect = dbTest.intersection(dibTrue, dibFalse);
    //Checks size of intersect -- should be two because two common elements between both bags
    assertEquals(2, bagIntersect.size(), 0);
    //Checks each element in intersect to see if they match common elements and correct order
    assertEquals("first element in both is String three", "three", bagIntersect.get(0));
    assertEquals("second element in both is String four", "four", bagIntersect.get(1));
    bagIntersect.remove("three");
    assertEquals("bagIntersect acts like regular bag, remove method still works", "four", bagIntersect.get(0));
    dibTrue.add("three");
    dibTrue.add("four");
    bagIntersectTwo = dbTest.intersection(dibTrue, dibFalse);
    //Checks size of intersect2 -- should be four because common elements appear twice in dibTrue
    assertEquals(4, bagIntersectTwo.size(), 0);
  }
  
  /*
   * Method to test difference method in DemoBag class
   */
  @Test
  public void testDifference() {
    DualImplementationBag<String> bagDifference = new DualImplementationBag<String>(true);
    DualImplementationBag<String> bagDifferenceTwo = new DualImplementationBag<String>(true);
    dibTrue.add("one");
    dibTrue.add("two");
    dibTrue.add("three");
    dibTrue.add("four");
    dibFalse.add("three");
    dibFalse.add("four");
    bagDifference = dbTest.difference(dibTrue, dibFalse);
    //Checks size of difference -- should be two because two elements appear in dibTrue but not dibFalse
    assertEquals(2, bagDifference.size(), 0);
    //Checks each element in difference to see if they match different elements and correct order
    assertEquals("first element in bag 1 but not bag 2", "one", bagDifference.get(0));
    assertEquals("second element in bag 1 but not bag 2", "two", bagDifference.get(1));
    bagDifference.remove("one");
    assertEquals("bagDifference acts like regular bag, remove method still works", "two", bagDifference.get(0));
    dibTrue.add("one");
    dibTrue.add("three");
    bagDifferenceTwo = dbTest.difference(dibTrue, dibFalse);
    //Checks the size of difference2 -- should be 3 because "one" appears twice in dibTrue 
    //and "three" appears in dibFalse
    assertEquals(3, bagDifferenceTwo.size(), 0);
  }
  
}