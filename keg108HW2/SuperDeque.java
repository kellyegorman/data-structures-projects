/**
 * @author Kelly Gorman
 * class that represents a circular array with stack and queue functions
 */
public class SuperDeque<E> {
  
  //a field that references the front of the deque
  private int front;
  
  //a field that references the back of the deque
  private int back;
  
  //an array stored in the SuperDeque instance
  private E[] dq;
  
  //the size that the array is set to when initialized
  private static final int DEFAULT_CAP = 1;
  
  //a field that represents the number of non-null elements in the array
  private int nElements = 0;
  
  /*
   * Creates and initializes a new instance of SuperDeque, setting front 
   * to zero, back to zero, and the initial size of the array to one
   */
  public SuperDeque() {
    this.dq = (E[]) new Object[DEFAULT_CAP];
    this.front = 0;
    this.back = 0;
  }
  
  /*
   * Returns a reference to the index 'front'
   * @return   int representing index of front
   */
  public int getFront() {
    return this.front;
  }
  
  /*
   * Allows change to index of front element in array, useful for QueueStackProblems
   * @param i  int represents new index in front
   */
  protected void setFront(int i) {
    front = i;
    if(!isEmpty() && dq[front] == null) {
      front = (front + 1)%dq.length;
    }
  }
  
  /*
   * Returns the index of the back of the array
   * @return   int representing index of back
   */
  public int getBack() {
    return this.back;
  }
  
  /*
   * Returns the length of the array, including null elements
   * @return    int representing number of spaces in array
   */
  public int getLength() {
    return dq.length;
  }
  
  /*
   * Returns the number of non-null elements in array
   * @return     int representing number of non-null elements
   */
  public int getNElements() {
    return nElements;
  }
  
  /*
   * Returns if the array has no room for more elements
   * @return   boolean representing if another element cannot be added
   */
  public boolean isFull() {
    return ((this.getBack() + 1) % dq.length) == this.getFront();
  }
  
  /*
   * Treats  array like a stack, pushes element by adding in front
   * of most recent element at front
   * @param element  element to be pushed in front of array
   */
  public void push(E element) {
    if (isFull()) {
      doubleSize();
    }
    front = (front - 1 + dq.length)%dq.length;
    dq[front] = element;
    nElements++;
  }
  
  /*
   * Treats array like a stack, pops element from front, setting space
   * at front to null, adjusting index, and returning element previously at front
   * @return   E element previously in front
   */
  public E pop() {
    if(isEmpty()) {
      return null;
    }
    else {
      E save = dq[front];
      dq[front] = null;
      front = (front + 1)%dq.length;
      nElements--;
      return save;
    }
  }
  
  /*
   * Treats array like a stack, peeks element at front, does not alter array or remove
   * @return   E element currently at front
   */
  public E peek() {
    if(isEmpty()) {
      return null;
    }
    else {
      return dq[front];
    }
  }
  
  /*
   * Treats array like queue, adds element in argument to back of queue 
   * @param element  E element to be added to back of array
   */
  public void enqueue(E element) {
    if (isFull()) {
      this.doubleSize();
    }
    dq[back] = element;
    back = (back + 1)%dq.length;
    nElements++;
  }
  
  /*
   * Treats array like a queue, removes element from the front of the queue
   * @return  E element previously at front of array
   */
  public E dequeue() {
    if(isEmpty()) {
      return null;
    }
    else {
      E save = dq[front];
      dq[front] = null;
      front = (front + 1)%dq.length;
      nElements--;
      return save;
    }
  }
  
  /*
   * Returns if there are no element in the array
   * @return   boolean if the array has no non-null elements
   */
  public boolean isEmpty() {
    return front == back;
  }
  
  /*
   * Doubles the size of the array and carries over all elements. Needed 
   * for push and enqueue methods. 
   */
  private void doubleSize() {
    int currentLength = dq.length;
    int newLength = currentLength * 2;
    E[] newQueue = (E[]) new Object[newLength];
    int i = front;
    int j = 0;
    while (i != back) {
      newQueue[j++] = dq[i];
      i = (i + 1) % currentLength;
    }
    dq = newQueue;
    front = 0;
    back = j;
  }
  
  /*
   * Returns a string representation of the array, without 
   * including null elements. Starts from the front and goes to the back.
   * @return   String representation of array
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int i = front;
    while (i != back) {
      sb.append(dq[i]);
      if ((i+1)%dq.length != back ) {
        sb.append(", ");
      }
      i = (i + 1)%dq.length;
    }
    return sb.toString();
  }
  
  /*
   * Prints elements in array. Useful for checking QueueStackProblems, specifically playGame
   */
  public void print() {
    for (E item : dq) {
      System.out.print(item);
    }
  }
  
}