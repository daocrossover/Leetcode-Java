package backtracking;

/* 46. Permutations
Description:
Given a collection of distinct integers, return all possible permutations.

Example:
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    // Solution: Backtracking
    // Note: the cur list should not contain replicates
    // Using contains method to check duplicates, slower
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> cur, int[] nums) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
        } else {
            for (int i = 0; i < nums.length; ++i) {
                if (cur.contains(nums[i])) continue;
                cur.add(nums[i]);
                backtrack(res, cur, nums);
                cur.remove(cur.size() - 1);
            }
        }
    }

    // Using visited array to check duplicates, faster
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrack(res, new ArrayList<>(), nums, visited);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> cur, int[] nums, boolean[] visited) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
        } else {
            for (int i = 0; i < nums.length; ++i) {
                if (visited[i]) continue;
                cur.add(nums[i]);
                visited[i] = true;
                backtrack(res, cur, nums, visited);
                cur.remove(cur.size() - 1);
                visited[i] = false;
            }
        }
    }
}
