package two_pointers;

/* 75. Sort Colors
Description:
Given an array with n objects colored red, white or blue,
sort them in-place so that objects of the same color are adjacent,
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Note: You are not suppose to use the library's sort function for this problem.

Example:
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's,
then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
 */

public class SortColors {
    // Dutch national flag problem
    // Solution1: Two Pointers, one pass
    // Time Complexity: O(n), Space Complexity: O(1)
    public void sortColors(int[] nums) {
        int start = 0, end = nums.length - 1;
        int i = 0;
        while (i <= end) {
            if (nums[i] == 0) {
                swap(nums, i, start);
                start++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, end);
                end--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // Solution2: Count Sort, two pass
    public void sortColors1(int[] nums) {
        int count0 = 0, count1 = 0;
        for (int num : nums) {
            if (num == 0) {
                count0++;
            }
            if (num == 1) {
                count1++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < count0) {
                nums[i] = 0;
            } else if (i < count0 + count1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }
}
