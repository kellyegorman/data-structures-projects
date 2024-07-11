/**
 * @author Kelly Gorman
 * class that contains findMin method, which returns minimum value in array using binary search
 */
public class Binary {

    /** Method that takes a sorted array and finds the minimum value using binary search
     * @param nums  sorted array of ints, not necessarily beginning with the smallest value
     * @return      int representing the smallest element of the input array
     */
    public int findMin(int[] nums) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        if (nums[rightIndex] >= nums[leftIndex]) {
            return nums[leftIndex];
        } 
        //middle is found to be the point between right and left Index, 
        //compare middle to right, update where left and right values are, call again until 
        //!(leftIndex<rightIndex)
        while (leftIndex < rightIndex) {
            int middle = leftIndex + (rightIndex - leftIndex) / 2;
            if (nums[middle] > rightIndex) {
                leftIndex = middle + 1;
            }
            else {
                rightIndex = middle;
            }
        }
        return nums[leftIndex];
    }
    
    public static void main(String[] args) {
    }
}