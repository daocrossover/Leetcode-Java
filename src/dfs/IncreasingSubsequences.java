package dfs;

/* 491. Increasing Subsequences
Description:
Given an integer array, your task is to find all the different
possible increasing subsequences of the given array,
and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates,
and two equal integers should also be considered as a special case of increasing sequence.
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(new LinkedList<Integer>(), 0, nums, res);
        return res;
    }

    private void dfs(LinkedList<Integer> list, int index, int[] nums, List<List<Integer>> res) {
        if (list.size() > 1) {
            res.add(new LinkedList(list));
        }
        // HashSet record what we have used in this particular recursion.
        Set<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; ++i) {
            if (used.contains(nums[i])) {
                continue;
            }
            if (list.size() == 0 || nums[i] >= list.peekLast()) {
                list.add(nums[i]);
                used.add(nums[i]);
                dfs(list, i+1, nums, res);
                list.remove(list.size() - 1);
            }
        }
    }
}
