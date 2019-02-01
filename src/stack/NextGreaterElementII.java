package stack;

/* 503. Next Greater Element II
Description:
Given a circular array (the next element of the last element is the first element of the array),
print the Next Greater Number for every element.
The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
which means you could search circularly to find its next greater number.
If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.

Note: The length of given array won't exceed 10000.
 */

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        // Solution1: Using Stack
        // to keep the indexes of the decreasing subsequence
        int n = nums.length;
        int[] next = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        // check the array for two times
        for (int i = 0; i < n * 2; ++i) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                next[stack.pop()] = num;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return next;
    }

    // Solution2: Brute Force
    public int[] nextGreaterElements1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < i + n; ++j) {
                if (nums[j % n] > nums[i]) {
                    res[i] = nums[j % n];
                    break;
                }
            }
        }
        return res;
    }
}
