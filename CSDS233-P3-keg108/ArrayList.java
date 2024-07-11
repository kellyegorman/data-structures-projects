/**
 * @author Kelly Gorman
 * class that represents an ArrayList of elements
 */
public class ArrayList {
  
  //field that references the "back" of the ArrayList
  private int back;
  
  //field that references the array stored in the ArrayList
  private Integer[] array;
  
  /*
   * Creates an initializes a new instance of an ArrayList. Back 
   * references the first index, 0, and length starts at 1
   */
  public ArrayList() {
    back = 0;
    this.array = new Integer[1];
  }
  
  /*
   * Returns the length of the array, including null elements -- for testing
   * @return length of array stored in ArrayList
   */
  public int getLength() {
    return this.array.length;
  }
  
  /*
   * Returns the element stored in the given index -- for testing
   * @param a  reference to index in ArrayList
   * @return   reference to element found at index a
   */
  public Integer getElement(int a) {
    return array[a];
  }
  
  /*
   * Returns if the ArrayList is "full" -- "last" index contains an element
   * @return  true if ArrayList is full and size needs to be doubled
   */
  private boolean isFull() {
    if(back == this.getLength()) {
      return true;
    }
    else{
      return false;
    }
  }
  
  /*
   * Doubles the size of the array contained in ArrayList if needed 
   */
  private void doubleSize() {
    if (isFull()) {
      int currentLength = this.getLength();
      int newLength = currentLength * 2;
      Integer[] newArray = new Integer[newLength];
      for(int i = 0; i < this.getLength(); i++) {
        newArray[i] = this.getElement(i);
      }
      int oldNum = this.getLength();
      this.array = newArray;
      this.back = oldNum;
    }
  }
  
  /*
   * Adds element to "back" of ArrayList 
   * @param n  reference to element added to back of array in ArrayList
   */
  public void add(int n) {
    doubleSize();
    while(array[back]!=null) {
      back = back + 1;
    }
    array[back] = n;
    back = back + 1;
  }
  
  /*
   * Adds element to array in ArrayList at given index -- replaces element
   * at index currently if already filled
   * @param n     element to be added at index
   * @param index index to add element to
   */
  public void add(int n, int index) throws IllegalArgumentException {
    if(index < 0) {
      throw new IllegalArgumentException();
    }
    if(index >= this.getLength()) {
      add(n);
    }
    else {
      doubleSize();
      for (int i = back; i > index; i--) {
        array[i] = array[i - 1];
      }
      array[index] = n;
      back++;
    }
  }
  
  /*
   * Returns the index of first instance of element 
   * @param n element to find index of first instance    
   * @return  int reference to index of first instance of n
   */
  public int indexof(int n) {
    int index = -1;
    int i = 0;
    while(index == -1 && i < (this.getLength() - 1)) {
      if(array[i].equals(n)) {
        index = i;
      }
      else {
        i++;
      }
    }
    return index;
  }
  
  /*
   * Removes the element at given index by setting this space to null
   * @param index  given index to remove element
   * @return       int referencing element that was removed from list
   */
  public int remove(int index) throws IllegalArgumentException{
    if (index < 0) {
      throw new IllegalArgumentException();
    }
    if(index >= back - 1) {
      Integer store1 = array[back -1];
      array[back -1] = null;
      back = back - 1;
      return store1;
    }
    Integer store = array[index];
    for(int i = index; i < back - 1; i++) {
      array[i] = array[i + 1];
    }
    back = back - 1;
    array[back] = null;
    return store;
  }
  
  /*
   * Removes the first instance of given element n from list
   * @param n  element to remove first instance from list
   */
  public void removeValue(int n) {
    int index = this.indexof(n);
    this.remove(index);
  }
  
  
  /*
   * Removes all instances of given element n from list
   * @param n  element to remove all instances from list
   */
  public void removeall(int n) {
    for(int i = back - 1; i >= 0; i--) {
      if(array[i].equals(n)) {
        this.remove(i);
      }
    }
  }
  
  /*
   * Returns the mean of all elements in list
   * @return  double representing the mean of all values in list
   */
  public double mean() {
    double sum = 0.0;
    double total = 0.0;
    for(int i = 0; i < this.getLength(); i++) {
      if(array[i]!=null) {
        sum+=array[i];
        total++;
      }
    }
    return sum/total;
  }
  
  /*
   * Returns the varianceof all elements in list
   * @return double representing the variance of all elements in list
   */
  public double variance() {
    double mean = this.mean();
    double numerator = 0.0;
    double total = 0.0;
    for(int i = 0; i < this.getLength(); i++) {
      if(array[i]!=null) {
        double difference = mean - array[i];
        double diff2 = difference*difference;
        numerator+=diff2;
        total++;
      }
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
  public ArrayList sublist(int lower, int upper) {
    ArrayList list2 = new ArrayList();
    for(int i = 0; i < back; i++) {
      if (this.array[i] >= lower && this.array[i] <= upper) {
        list2.add(this.array[i]);
      }
    }
    return list2;
  }
  
  /*
   * Returns a list that does not contain elements with a standard deviation greater than 3
   * @return   LinkedList containing only elements within three standard deviations from the mean
   */
  public ArrayList removeNoise() {
    double mean = this.mean();
    double dev = Math.sqrt(this.variance());
    double threeDevs = dev*3;
    double upper = mean + threeDevs;
    double lower = mean - threeDevs;
    ArrayList list2 = new ArrayList();
    for(int i = 0; i < this.back; i++) {
      int n = this.array[i];
      if(n >= lower && n <= upper) {
        list2.add(this.array[i]);
      }
    }
    return list2;
  }
  
  /*
   * Prints all elements in array stored in ArrayList for testing
   */
  public void print() {
    for(Integer i:array) {
      System.out.println(i);
    }
  }
  
}