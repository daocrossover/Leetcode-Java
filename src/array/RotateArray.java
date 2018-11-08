package array;

/* 189. Rotate Array
Description:
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:
Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 */

public class RotateArray {
    // Solution1: Using Extra Array
    // Time Complexity: O(n), Space Complexity: O(n)
    public void rotate(int[] nums, int k) {
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            tmp[(i + k) % nums.length] = nums[i];
        }

        for (int i = 0; i < nums.length; ++i) {
            nums[i] = tmp[i];
        }
    }

    // Solution2: Using Reverse
    // Example: [1,2,3,4,5,6,7] and k = 3
    // 1. [7,6,5,4,3,2,1]
    // 2. [5,6,7,4,3,2,1]
    // 3. [5,6,7,1,2,3,4]
    // Time Complexity: O(n), Space Complexity: O(1)
    public void rotate1(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    // Solution3: Swap the last k elements with the first k elements.
    // Example: [1,2,3,4,5,6,7] and k = 3
    // 1. [5,6,7,4,1,2,3]
    // 2. [5,6,7,1,2,3,4]
    // Time Complexity: O(n), Space Complexity: O(1)
    public void rotate2(int[] nums, int k) {
        int start = 0, n = nums.length;
        k %= n;
        while (n > 0 && k > 0) {
            // Swap the last k elements with the first k elements.
            // The last k elements will be in the correct positions
            // but we need to rotate the remaining (n - k) elements
            // to the right by k steps.
            for (int i = 0; i < k; i++) {
                swap(nums, i + start, n - k + i + start);
            }
            n -= k;
            start += k;
            k %= n;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
