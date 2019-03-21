package bit_manipulation;

/* 260. Single Number III
Description:
Given an array of numbers nums,
in which exactly two elements appear only once and all the other elements appear exactly twice.
Find the two elements that appear only once.

Example:
Input:  [1,2,1,3,2,5]
Output: [3,5]

Note:
1. The order of the result is not important.
So in the above example, [5, 3] is also correct.
2. Your algorithm should run in linear runtime complexity.
Could you implement it using only constant space complexity?
 */

/* 思路
同Single Number，首先还是利用异或，得到需要求的两个数的异或值，再得到该值最右一位为1的数
因为两个数相异或的结果中，如果某位为1，则证明要求的两个数的那位不相等。
根据这个原理将数组中的元素分成两组，其他的数异或完又变成0了，剩下的就是要求的两个数
 */

public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        // Pass 1:
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num: nums) {
            diff ^= num;
        }
        // find the lowest bit of the diff
        // let's say 6 (0110), -6 = 1010  0110 & 1010 = 0010
        int lowBit = diff & -diff;
        // since this bit from the result is 1, we can be sure that
        // a & lowBit and b & lowBit have different result
        int[] res = new int[]{0, 0};
        // Pass 2: Partition
        for (int num: nums) {
            if ((lowBit & num) == 0) {
                // the bit is not set
                res[0] ^= num;
            } else {
                // the bit is set
                res[1] ^= num;
            }
        }
        return res;
    }
}
