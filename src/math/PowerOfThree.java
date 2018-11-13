package math;

/* 326. Power of Three
Description:
Given an integer, write a function to determine if it is a power of three.

Example 1:
Input: 27
Output: true

Example 2:
Input: 0
Output: false

Example 3:
Input: 9
Output: true

Example 4:
Input: 45
Output: false

Follow up:
Could you do it without using any loop / recursion?
 */

public class PowerOfThree {
    // Solution1: Integer Limitations
    // Time Complexity: O(1)
    public boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19, 3^20 is bigger than int
        return (n > 0 && 1162261467 % n == 0);
    }

    // Solution2: Mathematics
    // n = 3^i
    // i = log3(n)
    // i = logb(b) / logb(3)
    public boolean isPowerOfThree1(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    // Solution3: Loop Iteration
    // Time Complexity: O(log3(n))
    public boolean isPowerOfThree2(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
