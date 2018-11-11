package bit_manipulation;

/* 136. Single Number
Description:
Given a non-empty array of integers, every element appears twice except for one.
Find that single one.

Note:
Your algorithm should have a linear runtime complexity.
Could you implement it without using extra memory?

Example 1:
Input: [2,2,1]
Output: 1

Example 2:
Input: [4,1,2,1,2]
Output: 4
 */

/* 思路
a XOR 0 = a
a XOR a = 0
利用异或的性质，相等的数异或之后为0，遍历完所有元素后，剩下的就是所求元素的值了
 */

import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
    // Solution1: XOR Solution
    // Space Complexity: O(1)
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num: nums) {
            res ^= num;
        }
        return res;
    }

    // Solution2: HashSet Solution
    // Space Complexity: O(n)
    public int singleNumber1(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num: nums) {
            if (!s.add(num)) {
                s.remove(num);
            }
        }
        return s.iterator().next();
    }
}
