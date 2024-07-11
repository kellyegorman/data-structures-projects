/**
 * @author Kelly Gorman
 * class that represents a Node in a list
 */
public class Node {
  
  //Field that represents an element stored in the node
  private Integer element;
  
  //Field that represents an instance of the node class, 
  //reference to the next node in a list
  private Node next;
  
  /*
   * Creates an initializes a new instance of Node.
   * @param element Int stored in the Node
   * @param next    reference to next Node in list
   */
  public Node(Integer element, Node next) {
    this.element = element;
    this.next = next;
  }
  
  /*
   * Returns element stored in Node
   * @return  Int element in Node
   */
  public Integer getElement() {
    return element;
  }
  
  /*
   * Returns next node in list
   * @return node after current node in list
   */
  public Node getNext() {
    return next;
  }
  
  /*
   * Changes or sets the element stored in the Node
   * @param element Int element stored in Node
   */
  public void setElement(Integer element) {
    this.element = element;
  }

  /*
   * Changes or sets the next node in list
   * @param next next node in list
   */
  public void setNext(Node next) {
    this.next = next;
  }
  
}