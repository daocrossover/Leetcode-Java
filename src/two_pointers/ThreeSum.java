package two_pointers;

/* 15. 3Sum
Description:
Given an array nums of n integers, are there elements a, b, c in nums
such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
The solution set must not contain duplicate triplets.

Example:
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums); // sort the array
        for (int i = 0; i < nums.length - 2; ++i) {
            if (nums[i] > 0) break; // if the first num > 0, stop searching
            if (i > 0 && nums[i] == nums[i-1]) continue; // skip duplicates
            // using two pointers
            int j = i + 1, k = nums.length - 1;
            int target = 0 - nums[i];
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j+1]) j++; // skip duplicates
                    while (j < k && nums[k] == nums[k-1]) k--; // skip duplicates
                    j++;
                    k--;
                } else if (nums[j] + nums[k] < target){
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}
