package hash_table.presum;

/* 560. Subarray Sum Equals K
Description:
Given an array of integers and an integer k,
you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2

Note:
1. The length of the array is in range [1, 20,000].
2. The range of numbers in the array is [-1000, 1000]
and the range of the integer k is [-1e7, 1e7].
 */

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    // Solution1: PreSum + HashMap
    // if sum[i] - sum[j] = k, the sum of elements lying between indices i and j is k.
    // Time Complexity: O(n), Space Complexity: O(n)
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    // Solution2: Brute Force
    // Time Complexity: O(n^2), Space Complexity: O(1)
    public int subarraySum1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            int sum = 0;
            for (int j = i; j < nums.length; ++j) {
                sum += nums[j];
                if (sum == k) {
                    res++;
                }
            }
        }
        return res;
    }
}
