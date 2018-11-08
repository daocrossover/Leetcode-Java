package bit_manipulation;

/* 201. Bitwise AND of Numbers Range
Description:
Given a range [m, n] where 0 <= m <= n <= 2147483647,
return the bitwise AND of all numbers in this range, inclusive.

Example 1:
Input: [5,7]
Output: 4

Example 2:
Input: [0,1]
Output: 0
 */

/* 思路
最后的数为m和n的左边最长公共前缀，如m=26，n=30
11010
11011
11100
11101
11110
最后相与后，为11000。
所以直接平移m和n，每次向右移一位，直到m和n相等
记录下所有平移的次数i，然后再把m左移i位即为最终结果
 */

public class BitwiseANDOfNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}
