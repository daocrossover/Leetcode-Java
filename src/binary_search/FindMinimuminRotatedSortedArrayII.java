package binary_search;

/* 154. Find Minimum in Rotated Sorted Array II
Description:
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
Find the minimum element.
The array may contain duplicates.

Example 1:
Input: [1,3,5]
Output: 1

Example 2:
Input: [2,2,2,0,1]
Output: 0

Note:
This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?
 */

/* Solution:
同样当A[mid] = A[end]时，无法判断min究竟在左边还是右边。
3 1 2 3 3 3 3
3 3 3 3 1 2 3
但可以肯定的是可以排除A[end]，因为即使min = A[end]，由于A[end] = A[mid]，排除A[end]并没有让min丢失
所以增加的条件是为：A[mid] = A[end]，搜索A[start : end-1]
 */

public class FindMinimuminRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                high--;
            }
        }
        return nums[low];
    }
}
