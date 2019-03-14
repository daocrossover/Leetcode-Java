package binary_search;

/* 153. Find Minimum in Rotated Sorted Array
Description:
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
Find the minimum element.
You may assume no duplicate exists in the array.

Example 1:
Input: [3,4,5,1,2]
Output: 1

Example 2:
Input: [4,5,6,7,0,1,2]
Output: 0
 */

/* Solution:
原数组: 0 1 2 4 5 6 7
情况:  6 7 0 1 2 4 5
情况:  2 4 5 6 7 0 1
(1) A[mid] < A[end]:
A[mid : end] sorted => min不在A[mid+1 : end]中，故搜索A[start : mid]
(2) A[mid] > A[end]:
A[start : mid] sorted且又因为该情况下A[end] < A[start] => min不在A[start : mid]中，故搜索A[mid+1 : end]
(3) base case:
a. start = end，必然A[start]为min，为搜寻结束条件。
b. start + 1 = end，此时A[mid] = A[start]，而min = min(A[mid], A[end])。而这个条件可以合并到(1)和(2)中。
 */

public class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }
}
