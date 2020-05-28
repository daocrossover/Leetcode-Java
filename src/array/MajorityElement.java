package array;

/* 169. Majority Element
Description:
Given an array of size n, find the majority element.
The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
Input: [3,2,3]
Output: 3

Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2
 */

import java.util.Arrays;
import java.util.HashMap;

public class MajorityElement {
    // Boyer-Moore Voting Algorithm:
    // Time complexity: O(n), Space complexity: O(1)
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count += 1;
            } else {
                count -= 1;
            }
        }
        return candidate;
    }

    // Sorting Solution:
    // Time complexity: O(n log n), Space complexity: O(1)
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    // HashMap Solution:
    // Time complexity: O(n), Space complexity: O(n)
    public int majorityElement3(int[] nums) {
        HashMap<Integer, Integer> myMap = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            if (!myMap.containsKey(num)) {
                myMap.put(num, 1);
            } else {
                myMap.put(num, myMap.get(num)+1);
            }
            if (myMap.get(num) > nums.length/2) {
                res = num;
                break;
            }
        }
        return res;
    }
}
