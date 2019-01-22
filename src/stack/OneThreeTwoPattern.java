package stack;

/* 456. 132 Pattern
Description:
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak
such that i < j < k and ai < ak < aj.
Design an algorithm that takes a list of n numbers as input and checks
whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]
Output: False
Explanation: There is no 132 pattern in the sequence.

Example 2:
Input: [3, 1, 4, 2]
Output: True
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].

Example 3:
Input: [-1, 3, 2, 0]
Output: True
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */

import java.util.Stack;

public class OneThreeTwoPattern {
    // Solution1: Stack
    // traverse the array from right to left, and find the element s3 which is larger than s2
    // then find the s1 which is smaller than s2
    // Time Complexity: O(n)
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int third = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; --i) {
            if (third > nums[i]) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                third = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }

    // Solution2: Find peak and bottom
    // For every [bottom, peak], find if there is one number bottom < number < peak.
    // Time Complexity:
    // O(n^2) (the worst case, increasing order), O(n) (the best case, decreasing order)
    public boolean find132pattern1(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int i = 0;
        while (i < nums.length - 1) {
            while (i < nums.length - 1 && nums[i] >= nums[i+1]) {
                i++;
            }
            // i is lowest now
            int j = i + 1;
            while (j < nums.length - 1 && nums[j] <= nums[j+1]) {
                j++;
            }
            // j is highest now
            int k = j + 1;
            while (k < nums.length) {
                if (nums[k] > nums[i] && nums[k] < nums[j]) {
                    return true;
                }
                k++;
            }
            i = j + 1;
        }
        return false;
    }
}
