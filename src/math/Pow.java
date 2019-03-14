package math;

/* 50. Pow(x, n)
Description:
Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:
Input: 2.00000, 10
Output: 1024.00000

Example 2:
Input: 2.10000, 3
Output: 9.26100

Example 3:
Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25

Note:
-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */

public class Pow {
    // Solution1: Iterative Solution
    public double myPow(double x, int n) {
        // if n is negative, -n may overflow, so use long
        long m = n > 0 ? n : -(long)n;
        double res = 1.0;
        while (m != 0) {
            if ((m & 1) == 1) {
                res *= x;
            }
            x *= x;
            m >>= 1;
        }
        return n >= 0 ? res : 1 / res;
    }

    // Solution2: Recursive Solution
    public double myPow1(double x, int n) {
        // if n is negative, -n may overflow, so use long
        long m = n > 0 ? n : -(long)n;
        double res = pow(x, n);
        if (n < 0) res = 1.0 / res;
        return res;
    }

    private double pow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        double res = pow(x, n >> 1);
        res *= res;
        if ((n & 1) == 1) res *= x;
        return res;
    }
}
