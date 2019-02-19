package binary_search;

/* 33. Search in Rotated Sorted Array
Description:
Suppose an array sorted in ascending order is rotated
at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search.
If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */

public class SearchInRotatedSortedArray {
    // Solution1: Binary Search based on the fact that
    // half of the array is in increasing order
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // find it
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[low] <= nums[mid]) {
                // if the left half is in increasing order
                if (target >= nums[low] && target < nums[mid]) {
                    // if the target is in this half
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // if the right half is in increasing order
                if (target > nums[mid] && target <= nums[high]) {
                    // if the target is in this half
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    // Solution2: Binary Search to find the index of the smallest value,
    // then use the normal binary search
    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        // lo == hi is the index of the smallest value and also the number of places rotated.
        int rot = low;
        low = 0;
        high = nums.length - 1;
        // the usual binary search and accounting for rotation.
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int realMid = (mid + rot) % nums.length;
            if (nums[realMid] == target) {
                return realMid;
            } else if (nums[realMid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
