package array;

/* 442. Find All Duplicates in an Array
Description:
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 */

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesInAnArray {
    // Solution1: Swap Solution:
    // Firstly, we put each element x in nums[x - 1].
    // Since x ranges from 1 to N, then x - 1 ranges from 0 to N - 1,
    // it won't exceed the bound of the array.
    // Secondly, we check through the array. If a number x doesn't present in nums[x - 1],
    // then x is absent.
    public List<Integer> findDuplicates(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[tmp - 1] = tmp;
            } else {
                i++;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    // Solution2:
    // when find a number i, flip the number at position i-1 to negative.
    // if the number at position i-1 is already negative, i is the number that occurs twice.
    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                res.add(Math.abs(index + 1));
            }
            nums[index] = -nums[index];
        }
        return res;
    }
}
