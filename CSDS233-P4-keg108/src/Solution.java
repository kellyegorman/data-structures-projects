import java.util.Arrays;
import java.util.Random;

/**
 * @author Kelly Gorman
 * class that contains the kClosest method, which find the k (int value) closest points to (233, 233)
 */
public class Solution {

    /** 
     * (FROM RANDOMQUICKSORT CLASS, modified for distance)
     * Randomly generates an int pivot point and partitions an array for quickSort 
     * -- private helper method
     * @param <T>    element in array type, Comparable type
     * @param nums   array to be partitioned
     * @param first  first element in array
     * @param last   last element in array
     * @return       int representing the index after partitioning occurs
     */
    private static int partition(int[][] nums, int first, int last) {
        Random r = new Random();
        int pivotPoint = r.nextInt(last - first + 1) + first;
        int[] pivotPoint2 = nums[pivotPoint];
        int[] store1 = nums[pivotPoint];
        nums[pivotPoint] = nums[last];
        nums[last] = store1;
        int firstElement = first;
        //from the first to last index, if an element's distance si less than or the same as the 
        //pivot's distance, move to left side, otherwise it stays on right 
        for (int i = first; i < last; i++) {
            if (distance(nums[i]) <= distance(pivotPoint2)) {
                int[] store3 = nums[i];
                nums[i] = nums[firstElement];
                nums[firstElement] = store3;
                firstElement++;
            }
        }
        int[] store2 = nums[pivotPoint];
        nums[pivotPoint] = nums[last];
        nums[last] = store2;
        return firstElement;
    }

    /**
     * Method that completes quicksort based on distance from certain point 
     * -- private helper method
     * @param nums   array to be sorted
     * @param first  location of first element of array to be sorted
     * @param last   location of last element of array to be sortd
     * @param k      location of element that is kth in order of distnace from point
     */
    private void quickSort(int[][] nums, int first, int last, int k) {
        if (first <= last) {
            int pivotPoint = partition(nums, first, last);
            if (pivotPoint == k - 1) {
                return;
            } 
            //right half, recursive calls sort this half
            else if (pivotPoint < k-1) {
                quickSort(nums, ++pivotPoint, last, k);
            } 
            //left half, recursive calls sort that half
            else {
                quickSort(nums, first, --pivotPoint, k);
            }
        }
    }

    /**
     * Finds the Euclidean distance, based on given equation, between input point p and (233, 233)
     * Euclidean distance formula = ((x2-x1)^2+(y2-y1)^2)^(1/2)
     * @param point array of coordinates, stored as [x, y]
     * @return the distance between the given point and the point [233, 233] using Euclidean distance
     */
    private static double distance(int[] p) {
        return Math.sqrt((p[0] - 233)*(p[0] - 233) + (p[1] - 233)*(p[1] - 233));
    }


     /**
     *  O(n*log(n)) or faster
     * @param points array of points, each point is [x, y]
     * @param k      number of points to return, closest to [233, 233] using Euclidean distance
     * @return       an array of the k closest points
     */
    public int[][] kClosest(int[][] points, int k) {
        quickSort(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);

    }

    public static void main(String[] args) {
    }

}