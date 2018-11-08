package bit_manipulation;

/* 190. Reverse Bits
Description:
Reverse bits of a given 32 bits unsigned integer.

Example:
Input: 43261596
Output: 964176192
Explanation: 43261596 represented in binary as 00000010100101000001111010011100,
             return 964176192 represented in binary as 00111001011110000010100101000000.
Follow up:
If this function is called many times, how would you optimize it?
 */

/* 思路
从右至左一位一位的处理，首先将res左移一位，如果n的最右一位为1，
则res++，然后将n右移一位去掉最右的一位。
 */

public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n == 0) return 0;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            if ((n & 1) == 1) res++;
            n >>= 1;
        }
        return res;
    }
}
