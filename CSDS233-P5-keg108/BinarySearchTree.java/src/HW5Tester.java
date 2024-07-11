import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author Kelly Gorman
 * class that contains tests for the BinarySearchTree class
 */
public class HW5Tester {
    
    BinarySearchTree b = new BinarySearchTree();

    /**
     * Test the contains method in the BinarySearchTree class
     */
    @Test
    public void testContains() {
        b.add(10);
        assertEquals("Root element is the element we are searching for", b.contains(10), true);
        b.delete(10);
        assertEquals("Element that was previously added and found to contain was removed", b.contains(10), false);
        b.add(10);
        b.add(8);
        b.add(12);
        b.add(11);
        b.add(7);
        b.add(9);
        b.add(1);
        b.add(100);
        assertEquals("Left child of root can be reached", b.contains(8), true);
        assertEquals("Right child of root can be reached", b.contains(12), true);
        assertEquals("Left child of right child of root can be reached", b.contains(11), true);
        assertEquals("Left child of left child of root can be reached", b.contains(7), true);
        assertEquals("Right child of left child of root can be reached", b.contains(9), true);
        assertEquals("Left child of left child of left child of root can be reached", b.contains(1), true);
        assertEquals("Right child of right child of root can be reached", b.contains(100), true);
        assertEquals("Element NOT ADDED to BST is not contained", b.contains(29), false);
    }

    /**
     * Test the add method in the BinarySearchTree class
     */
    @Test
    public void testAdd() {
        b.add(10);
        assertEquals("Value has been added, exists in b", b.contains(10), true);
        assertEquals("11 has not been added yet", b.contains(12), false);
        b.add(12);
        assertEquals("12 has been added, now contains the element", b.contains(12), true);
        b.add(8);
        b.add(7);
        b.add(9);
        b.add(11);
        b.add(13);
        //adding many elements increased the height
        assertEquals(3.0, b.height(), 0.0);
        assertEquals("Element from second row, 8, added successfully", b.contains(8), true);
        assertEquals("Element from third row, 7, added successfully", b.contains(7), true);
    }

    /**
     * Test the void method in the BinarySearchTree class
     */
    @Test
    public void testDelete() {
        b.add(10);
        assertEquals("Value has been added, exists in b", b.contains(10), true);
        b.delete(10);
        assertEquals("Value has been deleted, no longer exists in b", b.contains(10), false);
        b.add(2);
        b.add(1);
        b.add(3);
        assertEquals("Value has been added, exists in b", b.contains(1), true);
        assertEquals("Value has been added, exists in b", b.contains(3), true);
        b.delete(1);
        assertEquals("Value has been deleted, no longer exists in b", b.contains(1), false);
        assertEquals("Removing one value does not affect the others", b.contains(3), true);
        b.delete(3);
        assertEquals("Value has been deleted, no longer exists in b", b.contains(1), false);
        assertEquals("Value has been deleted, no longer exists in b", b.contains(3), false);
        assertEquals("Removing one value does not affect the others", b.contains(2), true);
    }

    /**
     * Test the height method in the BinarySearchTree class
     */
    @Test
    public void testHeight() {
        //Nothing added yet, root node is null, height is 0
        assertEquals(0.0, b.height(), 0.0);
        b.add(10);
        //Only the root, height is 1
        assertEquals(1.0, b.height(), 0.0);
        b.add(8);
        b.add(12);
        //Two children have been added, one parent with two children, height=2
        assertEquals(2.0, b.height(), 0.0);
        b.add(7);
        //One more element added = first element on third row, height=3
        assertEquals(3.0, b.height(), 0.0);
        b.add(13);
        b.add(9);
        b.add(11);
        //Third row is full, height is still=3
        assertEquals(3.0, b.height(), 0.0);
        b.delete(7);
        b.delete(9);
        b.delete(11);
        b.delete(13);
        //All elements in third row deleted, height is now 2 again
        assertEquals(2.0, b.height(), 0.0);
    }

    /**
     * Test the bfs method in the BinarySearchTree class
     */
    @Test
    public void testBFS() {
        b.add(10);
        b.add(8);
        b.add(12);
        b.add(7);
        b.add(13);
        assertEquals("String output matches", "10 8 12 7 13", b.bfs());
        b.add(9);
        b.add(11);
        assertEquals("String output matches", "10 8 12 7 9 11 13", b.bfs());
        b.delete(11);
        assertEquals("String output matches", "10 8 12 7 9 13", b.bfs());
    }

    /**
     * Test the toString method in the BinarySearchTree class
     */
    @Test
    public void testToString(){
        b.add(10);
        b.add(8);
        b.add(12);
        b.add(7);
        b.add(13);
        assertEquals("String version of appropriate tree traversal", "10 8 7 12 13", b.toString());
        b.add(9);
        b.add(11);
        assertEquals("String version of appropriate tree traversal", "10 8 7 9 12 11 13", b.toString());
        b.delete(9);
        b.delete(11);
        assertEquals("String version of appropriate tree traversal", "10 8 7 12 13", b.toString());
    }

    /**
     * Test the hasSum method in the BinarySearchTree class
     */
    @Test
    public void testHasSum() {
        b.add(10);
        b.add(8);
        b.add(12);
        b.add(7);
        b.add(9);
        b.add(11);
        b.add(13);
        assertEquals("10 -> 8 -> 7", b.hasSum(25),true);
        assertEquals("10 -> 8 -> 9", b.hasSum(27),true);
        assertEquals("10 -> 12 -> 11", b.hasSum(33),true);
        assertEquals("10 -> 12 -> 13", b.hasSum(35),true);
        assertEquals("No path for this sum exists", b.hasSum(50),false);
        assertEquals("No path for this sum exists", b.hasSum(2),false);
    }

    /**
     * Test the isSymmetrical method in the BinarySearchTree class
     */
    @Test
    public void testIsSymmetrical() {
        int[] a = {10, 8, 12, 7, 9, 11, 13};
        assertEquals("Tree I've been using for all testing is not symmetrical", BinarySearchTree.isSymmetrical(a), false);
        int[] arr = {1, 2, 2, 3, 4, 4, 3};
        assertEquals("Tree given in assignment example, is symmetrical", BinarySearchTree.isSymmetrical(arr), true);
        int[] arr2 = {1, 2, 2, 4, 3, 4, 3};
        assertEquals("Tree given in assignment example, is not symmetrical", BinarySearchTree.isSymmetrical(arr2), true);
        int[] arr3 = {1, 2, 2};
        assertEquals("Shorter symmetrical tree", BinarySearchTree.isSymmetrical(arr3), true);
        int[] arr4 = {1};
        assertEquals("One element is symmetrical", BinarySearchTree.isSymmetrical(arr4), true);
    }

}