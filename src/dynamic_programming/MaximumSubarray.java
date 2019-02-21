package dynamic_programming;

/* 53. Maximum Subarray
Description:
Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.

Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
which is more subtle.
 */

public class MaximumSubarray {
    // Solution1: Kadane's Algorithm
    // The improvement of Dynamic Programming
    // dp[i]: the maxSubArray for nums[0:i] which must has nums[i] as the end element.
    // dp[i] = (dp[i-1] > 0 ? dp[i-1] : 0) + nums[i]
    // the dp[i] only relates to dp[i-1], so we can use a variable to substitute dp array
    // Time Complexity: O(n), Space Complexity: O(1)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSoFar = nums[0], maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    // Solution2: Greedy
    // Keep a variable to store the current sum.
    // If the sum is below zero, then we reset it to the current number (start from current number).
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int curMax = Integer.MIN_VALUE;
        for (int num : nums) {
            if (sum >= 0) {
                sum += num;
            } else {
                sum = num;
            }
            curMax = Math.max(sum, curMax);
        }
        return curMax;
    }
}
