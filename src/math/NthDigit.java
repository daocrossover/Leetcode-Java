package math;

/* 400. Nth Digit
Description:
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:
Input:
3
Output:
3

Example 2:
Input:
11
Output:
0
Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0,
which is part of the number 10.
 */

/* Solution:
1-9，10-99，100-999，...
start从1开始，故首先n要减1，digits表示每组数字的长度，
n / 9 / start / digits >= 1判断n在哪一组数字中，利用整除避免overflow，
每次n要减去9 * start * digits，start乘10，digits加1
跳出循环后start + n / digits表示在所属数字
n % digits表示所属数字的哪一位
 */

public class NthDigit {
    public int findNthDigit(int n) {
        n--;
        int start = 1, digits = 1;
        while (n / 9 / start / digits >= 1) {
            n -= 9 * start * digits;
            start *= 10;
            digits++;
        }
        start += n / digits;
        return Integer.toString(start).charAt(n % digits) - '0';
    }
}
