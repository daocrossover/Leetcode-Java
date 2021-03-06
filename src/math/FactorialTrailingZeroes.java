package math;

/* 172. Factorial Trailing Zeroes
Description:
Given an integer n, return the number of trailing zeroes in n!.

Example 1:
Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Note: Your solution should be in logarithmic time complexity.
 */

/* 思路
1. 后缀0是由2，5相乘得来，因此只需看有多少个2，5即可
n = 5: 5!的质因子中 (2 * 2 * 2 * 3 * 5)包含一个5和三个2。因而后缀0的个数是1。
n = 11: 11!的质因子中(2^8 * 3^4 * 5^2 * 7)包含两个5和三个2。于是后缀0的个数就是2。

2. 质因子中2的个数总是大于等于5的个数。因此只要计数5的个数就可以了。
11中有两个5因此输出2.可用 n/5=2;

3. 需要注意的是25!中有25,20,15,10,5，但是25又可以分为5*5，
因此需要判断t=n/5后，t中的5的个数
 */

public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            n /= 5;
            res += n;
        }
        return res;
    }
}
