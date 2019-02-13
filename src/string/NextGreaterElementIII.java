package string;

/* 556. Next Greater Element III
Description:
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer
which has exactly the same digits existing in the integer n and is greater in value than n.
If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21

Example 2:
Input: 21
Output: -1
 */

public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        char[] nums = (n + "").toCharArray();
        int i = nums.length - 1;
        // 1. start from the right most digit and find the first digit that is
        // smaller than the digit next to it.
        for (; i >= 1; --i) {
            if (nums[i-1] < nums[i]) {
                break;
            }
        }
        // If no such digit is found, decreasing order(4321), return -1
        if (i == 0) {
            return -1;
        }
        // eg. 534976, i = 3
        // 2. find the smallest digit on right side of (i-1)'th
        // digit that is greater than number[i-1]
        // the smallest digit greater than 4 is 6.
        // finding from the right-most is OK, but may slower
        int x = nums[i-1];
        int j = i + 1, smallest = i;
        for (; j < nums.length; ++j) {
            if (nums[j] > x && nums[j] <= nums[smallest]) {
                smallest = j;
            }
        }
        // 3. swap the above found smallest digit with number[i-1]
        // -> 536974
        char tmp = nums[i-1];
        nums[i-1] = nums[smallest];
        nums[smallest] = tmp;
        // 4. sort the digits after (i-1) in ascending order (reverse)
        // Arrays.sort(nums, i, nums.length);
        reverse(nums, i);
        // 5. check overflow
        long res = Long.parseLong(new String(nums));
        return res <= Integer.MAX_VALUE ? (int) res : -1;
    }

    private void reverse(char[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            char tmp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = tmp;
        }
    }
}
