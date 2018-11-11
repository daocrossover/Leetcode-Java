package bit_manipulation;

/* 268. Missing Number
Description:
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
find the one that is missing from the array.

Example 1:
Input: [3,0,1]
Output: 2

Example 2:
Input: [9,6,4,2,3,5,7,0,1]
Output: 8

Note:
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant extra space complexity?
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingNumber {
    // Solution1: XOR Solution
    // Index	0	1	2	3
    // Value	0	1	3	4
    // missing = 4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
    //         = (4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
    //         = 0∧0∧0∧0∧2=2
    // Time Complexity: O(n), Space Complexity: O(1)
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; ++i) {
            res ^= (i ^ nums[i]);
        }
        return res;
    }

    // Solution2: Gauss' Formula
    // Time Complexity: O(n), Space Complexity: O(1)
    public int missingNumber1(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num: nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    // Solution3: HashSet Solution
    // Time Complexity: O(n), Space Complexity: O(n)
    public int missingNumber2(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) {
            numSet.add(num);
        }
        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; ++number) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }

    // Solution4: Sorting + Binary Search
    // Time Complexity: O(n log n), Space Complexity: O(1)
    public int missingNumber3(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
