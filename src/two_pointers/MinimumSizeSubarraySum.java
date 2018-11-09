package two_pointers;

/* 209. Minimum Size Subarray Sum
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

Example:
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

/* 思路
定义两个指针left和right，分别记录子数组的左右的边界位置，然后我们让right向右移，直到子数组和大于等于给定值或者right达到数组末尾
此时我们更新最短距离，并且将left像右移一位，然后再sum中减去移去的值，然后重复上面的步骤
直到right到达末尾，且left到达临界位置，即要么到达边界，要么再往右移动，和就会小于给定值。
 */

public class MinimumSizeSubarraySum {
    // Solution1: Using Two Pointers
    // Time Complexity: O(n)
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE, left = 0, sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            while (sum >= s) {
                minLen = Math.min(minLen, i - left + 1);
                sum -= nums[left++];
            }
        }
        return (minLen != Integer.MAX_VALUE) ? minLen : 0;
    }

    // Solution2: Using Binary Search
    // Time Complexity: O(n log n)
    public int minSubArrayLen1(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            if (end == sums.length) break;
            if (end - i < minLen) minLen = end - i;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private int binarySearch(int low, int high, int key, int[] sums) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sums[mid] >= key){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
