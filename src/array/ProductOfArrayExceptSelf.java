package array;

/* 238. Product of Array Except Self
Description:
Given an array nums of n integers where n > 1,
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity?
(The output array does not count as extra space for the purpose of space complexity analysis.)
 */

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int tmp = 1;
        // First, calculate the running product of the part before the current number.
        for (int i = 0; i < nums.length; ++i) {
            res[i] = tmp;
            tmp *= nums[i];
        }
        tmp = 1;
        // Second, calculate the running product of the part after the current number
        for (int i = nums.length - 1; i >= 0; --i) {
            res[i] *= tmp;
            tmp *= nums[i];
        }
        return res;
    }
}
