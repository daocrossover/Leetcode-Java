package bit_manipulation;

/* 397. Integer Replacement
Description:
Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:
Input:
8
Output:
3
Explanation:
8 -> 4 -> 2 -> 1

Example 2:
Input:
7
Output:
4
Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
 */

public class IntegerReplacement {
    // Solution1: Using bitCount()
    // remove as many 1's as possible, doing +1 in case of a tie except n == 3 (-1)
    // If n is even, halve it.
    // If n=3 or n-1 has less 1's than n+1, decrement n.
    // Otherwise, increment n.
    public int integerReplacement(int n) {
        int c = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n >>>= 1;
            } else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {
                --n;
            } else {
                ++n;
            }
            ++c;
        }
        return c;
    }

    // Solution2:
    // if a number ends with 01, then certainly decrementing is the way to go.
    // Otherwise, if it ends with 11, then certainly incrementing is at least as good as decrementing
    // (*011 -> *010 / *100) or even better (if there are three or more 1's).
    public int integerReplacement1(int n) {
        int c = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n >>>= 1;
            } else if (n == 3 || ((n >>> 1) & 1) == 0) {
                --n;
            } else {
                ++n;
            }
            ++c;
        }
        return c;
    }
}
