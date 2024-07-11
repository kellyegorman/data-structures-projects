/**
 * @author Kelly Gorman
 * class that represents a linked list of nodes
 */
public class LinkedList{
  
  //Field that represents the first node in a LinkedList
  private Node firstNode;
  
  //Field that represents a Node in the list
  private Node nodePointer;
  
  /*
   * Creates an initializes a new instance of a LinkedList.
   * @param element Int stored in the Node
   * @param next    reference to next Node in list
   */
  public LinkedList() {
    firstNode = new Node(null, null);
  }
  
  /*
   * Returns the first Node in a LinkedList
   * @return Node at the first index of list
   */
  public Node getFirstNode() {
    return firstNode;
  }
  
  /*
   * Returns the element stored in the first Node in a LL
   * @return Int element in first node
   */
  public Integer firstElement() {
    return getFirstNode().getElement();
  }
  
  /*
   * Sets the element stored in the first Node in a LL
   * @param node element set to first node
   */
  public void setFirstNode(Node node) {
    this.firstNode = node;
  }
  
  /*
   * Returns the number of nodes in the LinkedList
   * @return  int number of nodes in list
   */
  public int length() {
    int lengthSoFar = 0;
    nodePointer = getFirstNode();
    while (nodePointer != null) {
      lengthSoFar++;
      nodePointer = nodePointer.getNext();
    }
    return lengthSoFar;
  }
  
  /*
   * Adds a new node with element in LinkedList
   * @param n element to be added to new Node at end of LL
   */
  public void add(int n) {
    if(firstElement() == null) {
      firstNode.setElement(n);
    }
    else {
      nodePointer = getFirstNode();
      while (nodePointer.getNext() != null) {
        nodePointer = nodePointer.getNext();
      }
      nodePointer.setNext(new Node(n, null));
    }
  }
  
  /*
   * Adds a new Node with element n to LinkedList at given index, assume
   * index starts counting at 0
   * @param n      element to be added to new node 
   * @param index  index (starts counting at 0) to add new element at in LL
   */
  public void add(int n, int index) throws IllegalArgumentException{
    if(index < 0) {
      throw new IllegalArgumentException();
    }
    if(index >= length()) {
      this.add(n);
    }
    else {
      int counter = 1;
      nodePointer = getFirstNode();
      while(counter<index) {
        nodePointer = nodePointer.getNext();
        counter++;
      }
      Node nextNode = nodePointer.getNext();
      nodePointer.setNext(new Node(n, nextNode));
    }
  }
  
  /*
   * Returns the index that first instance of given element can be found at
   * @param n  int in list to find index of
   * @return   int representing index of first instance of n, -1 if LL doesnt contain n
   */
  public int indexof(int n) {
    nodePointer = getFirstNode();
    int index = 0;
    while(nodePointer != null) {
      if(nodePointer.getElement() == n) {
        return index;
      }
      nodePointer = nodePointer.getNext();
      index++;
    }
    return -1;
  }
  
  /*
   * Removes element at given index and returns removed element
   * @param index  index to remove element at
   * @return       int representing removed element
   */
  public int remove(int index) throws IllegalArgumentException {
    if (index < 0) {
      throw new IllegalArgumentException();
    }
    int length = length();
    Node pointer = getFirstNode();
    if (index >= length) {
      for(int i = 1; i < length - 1; i++) {
        pointer = pointer.getNext();
      }
      int returnMe = pointer.getNext().getElement();
      pointer.setNext(null);
      return returnMe;
    }
    int removedValue;
    if (index == 0) {
      removedValue = getFirstNode().getElement();
      setFirstNode(getFirstNode().getNext());
    } 
    else {
      Node temp = getFirstNode();
      for (int i = 0; i < index - 1; i++) {
        temp = temp.getNext();
      }
      removedValue = temp.getNext().getElement();
      temp.setNext(temp.getNext().getNext());
    }
    return removedValue;
  }
  
  /*
   * Removes first instance given value from list
   * @param n  given element to remove first instance from list
   */
  public void removeValue(int n) {
    int index = this.indexof(n);
    this.remove(index);
  }
  
  /*
   * Removes all instances of given value from list
   * @param n  given element to remove all instances from list
   */
  public void removeall(int n) {
    Node pointer = getFirstNode();
    Node checkNode1 = null;
    while (pointer != null) {
      if (pointer.getElement().equals(n)) {
        if (checkNode1 == null) {
          setFirstNode(pointer.getNext());
          pointer = getFirstNode(); 
        } 
        else {
          checkNode1.setNext(pointer.getNext());
          pointer = pointer.getNext(); 
        }
      } 
      else {
        checkNode1 = pointer;
        pointer = pointer.getNext();
      }
    }
  }
  
  /*
   * Returns the mean of all elements in list
   * @return  double representing the mean of all elements in list
   */
  public double mean() {
    Node pointer = getFirstNode();
    double total = 0.0;
    double count = 0.0;
    while(pointer != null) {
      total+=pointer.getElement();
      count+=1.0;
      pointer = pointer.getNext();
    }
    double mean = total/count;
    return mean;
  }
  
  /*
   * Returns the varianceof all elements in list
   * @return double representing the variance of all elements in list
   */
  public double variance() {
    double mean = this.mean();
    double numerator = 0.0;
    double total = 0.0;
    Node pointer = getFirstNode();
    while(pointer.getNext() != null) {
      double difference = mean - pointer.getElement();
      double diff2 = difference*difference;
      numerator+=diff2;
      total++;
      pointer = pointer.getNext();
    }
    double denominator = total - 1;
    double var = numerator/denominator;
    return java.lang.Math.sqrt(var);
  }
  
  /*
   * Returns a sublist of elements in list that are greater than given lower 
   * and less than given upper constraints 
   * @param lower  int representing minimum of elements in sublist
   * @param upper  int representing maximum of elements in sublist
   * @return       LinkedList containing elements between lower and upper
   */
  public LinkedList sublist(int lower, int upper) {
    LinkedList list2 = new LinkedList();
    Node pointer = getFirstNode();
    while(pointer != null) {
      if(pointer.getElement() >= lower && pointer.getElement() <= upper) {
        list2.add(pointer.getElement());
      }
      pointer = pointer.getNext();
    }
    return list2;
  }
  
  /*
   * Returns a list that does not contain elements with a standard deviation greater than 3
   * @return   LinkedList containing only elements within three standard deviations from the mean
   */
  public LinkedList removeNoise() {
    LinkedList list2 = new LinkedList();
    double mean = this.mean();
    double dev = Math.sqrt(this.variance());
    double threeStDevs = dev*3;
    Node pointer = getFirstNode();
    while(pointer != null) {
      if((pointer.getElement() >= (mean - threeStDevs)) &&
         (pointer.getElement() <= (mean + threeStDevs))) {
        list2.add(pointer.getElement());
      }
      pointer = pointer.getNext();
    }
    return list2;
  }
  
}