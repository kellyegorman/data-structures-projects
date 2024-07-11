import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * @author Kelly Gorman
 * Class that represents an ADT whose internal data structure may swith 
 * between ArrayList and LinkedList. All methods should work for either structure.
 */
public class DualImplementationBag_2<T> {
  
  //Field that represents whether the internal data structure is an ArrayList
  private boolean useArrayList;
  
  //Field that represents the list stored in the bag
  private List<T> internalList;
  
  /*
   * Creates a new instead of DualImplementationBag
   * Creates a new empty List stored (Array or Linked based on useArrayList boolean), and
   * initializes internalList field with this new List
   * @param useArrayList  Initializes the useArrayList field to determine internal structure
   */
  public DualImplementationBag_2(boolean useArrayList) {
    if (useArrayList) {
      this.internalList = new ArrayList<T>();
      this.useArrayList = useArrayList;
    }
    else {
      this.internalList = new LinkedList<T>();
      this.useArrayList = useArrayList;
    }
  }
  
  /* 
   * Sets the internal data structure to either ArrayList or LinkedList and moves all elements
   * from previous list to new list. Changes internalList and useArrayList fields
   * @param useArrayList  True if structure should be ArrayList, false if structure should be LinkedList
   */
  public void setUseArrayList(boolean useArrayList) {
    if(useArrayList) {
      LinkedList<T> lL = new LinkedList<T>();
      for(int i = 0; i < internalList.size(); i++) {
        lL.add(this.internalList.get(i));
      }
      internalList = lL;
      this.useArrayList = true;
    } 
    else {
      ArrayList<T> aL = new ArrayList<T>();
      for(int i = 0; i < internalList.size(); i++) {
        aL.add(this.internalList.get(i));
      }
      internalList = aL;
      this.useArrayList = false;
    }
  }
  
  /*
   * Allows checking internal data structure, easier for testing
   * @return   True represents ArrayList, false represents LinkedList
   */
  public boolean getUseArrayList() {
    return this.useArrayList;
  }
  
  /*
   * Add an element to the list using the List add method
   * @param element  Element to be added to the list
   * @return         True represents successfully added element to list
   */
  public boolean add(T element) {
      return internalList.add(element);
  }
  
  /*
   * Remove an element from list usingthe List remove method
   * @param element  Element to be removed from the list
   * @return         True represents successfully removed element from list
   */
  public boolean remove(T element) {
      return internalList.remove(element);
  }
  
  /* 
   * Checks if element in parameter is present in the bag's list
   * @param element  Element to be checked if it is present in the bag
   * @return         True if element is in bag, false if element is not in bag
   */
  public boolean contains(T element) {
    boolean elementIsIncluded = false;
    for(int i = 0; i < internalList.size(); i++) {
      if(internalList.get(i).equals(element)) {
        elementIsIncluded = true;
      }
    }
    return elementIsIncluded;
  }
  
  /*
   * Checks if there are any elements in the bag
   * @return   True if there are no elements in bag, false if there are any 
   */
  public boolean isEmpty() {
    boolean hasNoElements = true;
    for(int i = 0; i < internalList.size(); i++) {
      if(internalList.get(i) != null) { 
        hasNoElements = false;
      }
    }
    return hasNoElements;
  }
  
  /*
   * Finds how many elements are in bag
   * @return    Number of elements in bag
   */
  public int size() {
    int lengthSoFar = 0;
    for(T element:internalList) {
      lengthSoFar++;
    }
    return lengthSoFar;
  }
  
  /*
   * Finds how many times element in parameter appears in a bag
   * @param element Element to be counted in the bag
   * @return        Number of times element in parameter appears
   */
  public int getFrequencyOf(T element){
    int frequencyOf = 0;
    for(int i = 0; i < internalList.size(); i++) {
      if(internalList.get(i).equals(element)) {
        frequencyOf++;
      }
    }
    return frequencyOf;
  }
  
  /*
   * Uses get method in List to find the element at a given index
   * @param index  Index in list to have element return 
   * @return       Element found at index in parameter
   */
  public T get(int index) {
    return internalList.get(index);
  }
  
}