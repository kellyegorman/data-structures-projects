import java.util.Random;

/**
 * @author Kelly Gorman
 * class that contains the quickSort method, which sorts elements in array using a random partition
 */
public class RandomQuickSort {
    /**
     * Randomly generates an int pivot point and partitions an array for quickSort
     * @param <T>    element in array type, Comparable type
     * @param nums   array to be partitioned
     * @param first  first element in array
     * @param last   last element in array
     * @return       int representing the index after partitioning occurs
     */
    private static <T extends Comparable<? super T>> int partition(T[] nums, int first, int last) {
        Random r = new Random();
        int pivotPoint = r.nextInt(last - first + 1) + first;
        T pivotElement = nums[pivotPoint];
        nums[pivotPoint] = nums[last];
        nums[last] = pivotElement;
        int firstElement = first;
        //from the first to last index, if an element is less that pivot, moves to left of pivot,
        //otherwise the element stays on the right of pivot
        for (int i = first; i < last; i++) {
            if (nums[i].compareTo(pivotElement) <= 0) {
                T store = nums[i];
                nums[i] = nums[firstElement];
                nums[firstElement] = store;
                firstElement++;
            }
        }
        T store2 = nums[firstElement];
        nums[firstElement] = nums[last];
        nums[last] = store2;
        return firstElement;
    }

    /**
     * @param <T>   element in array type, Comparable type
     * @param nums  array to be sorted
     * @param first first element in array 
     * @param last  last element in array
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] nums, int first, int last) {
        if (last >= first) {
            int pivotPoint = partition(nums, first, last);
            //recursive calls to repeatedly sort left and right halves after partitioning
            quickSort(nums, first, --pivotPoint);
            quickSort(nums, ++pivotPoint, last);
        }
    }
    public static void main(String[] args) {
    }


}