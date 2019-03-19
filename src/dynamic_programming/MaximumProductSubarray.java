package dynamic_programming;

/* 152. Maximum Product Subarray
Description:
Given an integer array nums, find the contiguous subarray within an array
(containing at least one number) which has the largest product.

Example 1:
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], min = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] >= 0) {
                max = Math.max(nums[i], max * nums[i]);
                min = Math.min(nums[i], min * nums[i]);
            } else {
                int tmp = max;
                max = Math.max(nums[i], min * nums[i]);
                min = Math.min(nums[i], tmp * nums[i]);
            }
            res = Math.max(res, max);
        }
        return res;
    }
}
