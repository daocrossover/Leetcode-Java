package binary_search;

/* 540. Single Element in a Sorted Array
Description:
Given a sorted array consisting of only integers where every element appears
twice except for one element which appears once.
Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2

Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10

Note: Your solution should run in O(log n) time and O(1) space.
 */

public class SingleElementInSortedArray {
    // Solution1:
    // find the first index whose "partner index" (the index xor 1) holds a different value.
    // partners: 0 & 1 and 2 & 3 ...
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
