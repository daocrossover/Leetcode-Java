package dynamic_programming;

/* 300. Longest Increasing Subsequence
Description:
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Note:
There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 */

public class LongestIncreasingSubsequence {
    // Solution1: Dynamic Programming
    // dp[i] represents the the length of the LIS till nums[i].
    // Time Complexity: O(n^2), Space Complexity: O(n)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        int maxLen = 1;
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    // Solution2: Dynamic Programming + Binary Search
    // tails is an array storing the smallest tail of all increasing subsequence with length i+1 in tails[i].
    // For example, say we have nums = [4,5,6,3], then all the available increasing subsequence are:
    // len = 1:      [4], [5], [6], [3]   => tails[0] = 3
    // len = 2:      [4, 5], [5, 6]       => tails[1] = 5
    // len = 3:      [4, 5, 6]            => tails[2] = 6
    // Time Complexity: O(n log n), Space Complexity: O(n)
    public int lengthOfLIS1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int len = 0;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > dp[len]) {
                dp[++len] = nums[i];
            }
            else {
                int index = search(dp, len, nums[i]);
                dp[index] = nums[i];
            }
        }
        return len + 1;
    }

    private int search(int[] dp, int len, int val) {
        int start = 0;
        while(start <= len) {
            int mid = start + (len - start) / 2;
            if(dp[mid] == val) {
                return mid;
            }
            else if(dp[mid] < val) {
                start = mid + 1;
            }
            else {
                len = mid - 1;
            }
        }
        return start;
    }
}
