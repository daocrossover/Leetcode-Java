package two_pointers;

/* 167. Two Sum II - Input array is sorted
Description:
Given an array of integers that is already sorted in ascending order,
find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target,
where index1 must be less than index2.

Note:
1. Your returned answers (both index1 and index2) are not zero-based.
2. You may assume that each input would have exactly one solution and you may not use the same element twice.

Example:
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */

import java.util.HashMap;

public class TwoSumInputArrayIsSorted {
    // Using two pointers
    // Time Complexity: O(n), Space Complexity: O(1)
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int start = 0, end = numbers.length - 1;
        while (numbers[start] + numbers[end] != target) {
            if (numbers[start] + numbers[end] < target) {
                start++;
            } else {
                end--;
            }
        }
        res[0] = start + 1;
        res[1] = end + 1;
        return res;
    }

    // Using HashMap
    // Time Complexity: O(n), Space Complexity: O(n)
    public int[] twoSum1(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; ++i) {
            if (map.containsKey(numbers[i])) {
                return new int[]{map.get(numbers[i]), i+1};
            } else {
                map.put(target - numbers[i], i+1);
            }
        }
        return null;
    }

    // Using binary search
    // Time Complexity: O(n log n), Space Complexity: O(1)
    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int start = i + 1, end = numbers.length - 1;
            int gap = target - numbers[i];
            while (start <= end) {
                int mid = start + (end - start)/2;
                if (numbers[mid] == gap) {
                    return new int[]{i + 1, mid + 1};
                } else if(numbers[mid] > gap) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return null;
    }
}
