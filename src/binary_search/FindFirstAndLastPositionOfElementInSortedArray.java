package binary_search;

/* 34. Find First and Last Position of Element in Sorted Array
Description:
Given an array of integers nums sorted in ascending order,
find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 */

public class FindFirstAndLastPositionOfElementInSortedArray {
    // Solution: Binary Search
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // if target == nums[mid], high = mid - 1
            // when low == high && target == nums[mid], high = mid - 1,
            // then the loop is over, the low index is what should be found
            if (target <= nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // check if the low is over the range of the array and
        // if nums[low] is equal to target
        if (low == nums.length || nums[low] != target) {
            return res;
        }
        res[0] = low;
        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // if target == nums[mid], low = mid + 1
            // when low == high && target == nums[mid], low = mid + 1,
            // then the loop is over, the high index is what should be found
            if (target >= nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        res[1] = high;
        return res;
    }
}
