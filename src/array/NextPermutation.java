package array;

/* 31. Next Permutation
Description:

 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        // 1. start from the right most digit and find the first digit that is
        // smaller than the digit next to it.
        int i = nums.length - 1;
        for (; i >= 1; --i) {
            if (nums[i-1] < nums[i]) {
                break;
            }
        }
        // If no such digit is found, decreasing order(4321), just reverse
        if (i != 0) {
            // eg. 534976, i = 3
            // 2. find the smallest digit on right side of (i-1)'th
            // digit that is greater than number[i-1]
            // the smallest digit greater than 4 is 6.
            int x = nums[i-1];
            int j = nums.length - 1;
            for (; j >= i; --j) {
                if (nums[j] > x) {
                    break;
                }
            }
            // 3. swap the above found smallest digit with number[i-1]
            // -> 536974
            int tmp = nums[i-1];
            nums[i-1] = nums[j];
            nums[j] = tmp;
        }
        // 4. sort the digits after (i-1) in ascending order (reverse)
        reverse(nums, i);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            int tmp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = tmp;
        }
    }
}
