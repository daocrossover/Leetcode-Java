package bit_manipulation;

/* 191. Number of 1 Bits
Description:
Write a function that takes an unsigned integer and
returns the number of '1' bits it has (also known as the Hamming weight).

Example 1:
Input: 11
Output: 3
Explanation: Integer 11 has binary representation 00000000000000000000000000001011

Example 2:
Input: 128
Output: 1
Explanation: Integer 128 has binary representation 00000000000000000000000010000000
 */

/* Solution:
Solution1:
如果判断数字最右边的数是不是1，然后将输入的数右移一位，直到数字为0，则当输入的数为负数时，会出现死循环。
故采用一个mask位，将其左移判断每一位是否为1。

Solution2:
将一个数减1，再和原数字做与运算，会把这个数最右边的1变为0。那么这个数的二进制表示有多少个1，
就可以进行多少次这样的操作。
 */

public class NumberOf1Bits {
    // you need to treat n as an unsigned value

    // Solution1: Loop and Flip
    public int hammingWeight(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; ++i) {
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }

    // Solution2: Bit Manipulation Trick
    public int hammingWeight1(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
}
