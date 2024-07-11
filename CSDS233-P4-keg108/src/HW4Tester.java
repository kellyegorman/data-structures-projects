import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author Kelly Gorman
 * class that contains tests for the Binary, RandomQuickSort, and Solution classes
 */
public class HW4Tester {

    //Field that represents an instance of the Binary class in order to test an instance method
    Binary b = new Binary();
    //Fields that represent array to test findMin method
    int[] i1 = {1, 2, 3, 4, 5};
    int[] i2 = {2, 3, 4, 5, 1};
    int[] i3 = {4, 5, 1, 2, 3};
    int[] i4 = {3, 4, 5, 1, 2};
    int[] i5 = {200, 0, 100};
    int[] i6 = {1};
    int[] i7 = {11, 3, 4, 5, 6, 7, 8, 9, 10};

    /**
     * Test the findMin function in the Binary class
     * @param 
     * @return void
     */
    @Test
    public void testBinary() {
    //First element is smallest
    assertEquals(1, b.findMin(i1), 0.0);
    //Last element is smallest
    assertEquals(1, b.findMin(i2), 0.0);
    //Middle element is smallest
    assertEquals(1, b.findMin(i3), 0.0);
    //Smallest is in third quarter
    assertEquals(1, b.findMin(i4), 0.0);
    //Middle element is smallest and numberes are large and not incremental by one
    assertEquals(0, b.findMin(i5), 0.0);
    //Only one element
    assertEquals(1, b.findMin(i6), 0.0);
    //Longer list, smallest is in the first quarter
    assertEquals(3, b.findMin(i7), 0.0);
    }

    //Field that represents an instance of the RandomQuickSort class in order to test an instance method
    RandomQuickSort rqs = new RandomQuickSort();
    //Fields that represent Integer array to test quickSort method
    Integer[] r1 = {1, 2, 3, 4, 5};
    Integer[] r1answer = {1, 2, 3, 4, 5};
    Integer[] r2 = {3, 1689, 7, 45, 9};
    Integer[] r2answer = {3, 7, 9, 45, 1689};
    Integer[] r3 = {400, 399, 299, 50};
    Integer[] r3answer = {50, 299, 399, 400};
    Integer[] r4 = {10, 9, 8, 7, 6, 1, 2, 3, 5, 4};
    Integer[] r4answer = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Integer[] r5 = {1, 1, 1, 1};
    Integer[] r5answer = {1, 1, 1, 1};

    /**
     * Test the quickSort function in the RandomQuickSort class
     * @param 
     * @return void
     */ 
    @Test
    public void testRandomQuickSort() {
        //Already sorted array, matches array identical to original
        RandomQuickSort.quickSort(r1, 0, r1.length - 1);
        assertArrayEquals(r1, r1answer);
        //Unsorted array with wide-ranging elements
        RandomQuickSort.quickSort(r2, 0, r2.length-1);
        assertArrayEquals(r2, r2answer);
        //Unsorted array whose first digits would sort in a different order than the full ints
        RandomQuickSort.quickSort(r3, 0, r3.length -1);
        assertArrayEquals(r3, r3answer);
        //Array with more elements (10) that is partially sorted before quickSort called
        RandomQuickSort.quickSort(r4, 0, r4.length-1);
        assertArrayEquals(r4, r4answer);
        //Array of all the same element, matches array idential to the original after calling qS
        RandomQuickSort.quickSort(r5, 0, r5.length-1);
        assertArrayEquals(r5, r5answer);
    }

    //Field that represents an instance of the Solution class in order to test an instance method
    Solution s = new Solution();
    //Fields that represent arrays of arrays, which represent (x,y) points, to test teh kClosest method
    int[][] ex1 = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}};
    int[][] ex2 = {{234, 232}, {231, 233}, {100, 100}};

    /** 
     * Test the kClosest function in the Solution class
     * @param 
     * @return void
     */
    @Test
    public void testKClosest() {
        //Find the three closest points
        int[][] ex1answer1 = {{5,5}, {4,4}, {3,3}};
        assertArrayEquals(ex1answer1, s.kClosest(ex1, 3));
        //Find the two closest points using the same method
        int[][] ex1answer2 = {{5,5},{4,4}};
        assertArrayEquals(ex1answer2, s.kClosest(ex1, 2));
        //Given an array of three elements, where two are close to the point and one is not, 
        //the closest and second closest should be the ones near the point
        int[][] ex2answer1 = {{234, 232}};
        assertArrayEquals(ex2answer1, s.kClosest(ex2, 1));
        int[][] ex2answer2 = {{234, 232}, {231, 233}};
        assertArrayEquals(ex2answer2, s.kClosest(ex2, 2));
    }

}