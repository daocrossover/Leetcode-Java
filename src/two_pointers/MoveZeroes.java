package two_pointers;

/* 283. Move Zeroes
Description:
Given an array nums, write a function to move all 0's to the end of it
while maintaining the relative order of the non-zero elements.

Example:
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */

public class MoveZeroes {
    // Solution1: Using Two Pointers
    public void moveZeroes(int[] nums) {
        for (int i = 0, j = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j++;
            }
        }
    }

    // Solution2: Using Insert Index
    // Shift non-zero values as far forward as possible
    // Fill remaining space with zeros
    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
