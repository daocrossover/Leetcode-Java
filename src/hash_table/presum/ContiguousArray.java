package hash_table.presum;

/* 525. Contiguous Array
Description:
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000.
 */

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    // Solution1: PreSum + HashMap
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; ++i) {
            // change 0 in the original array to -1
            sum += 2 * nums[i] - 1;
            // if find the sum[i, j] == 0
            // then we know there are even number of -1 and 1 between index i and j.
            if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }
}
