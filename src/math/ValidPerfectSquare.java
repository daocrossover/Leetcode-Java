package math;

/* 367. Valid Perfect Square
Description:
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:
Input: 16
Output: true

Example 2:
Input: 14
Output: false
 */

public class ValidPerfectSquare {
    // Solution1: Binary Search
    // Time Complexity: O(log n)
    public boolean isPerfectSquare(int num) {
        int low = 1, high = num;
        while (low <= high) {
            // use long for mid to avoid mid*mid from overflow
            long mid = low + (high - low) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = (int)mid + 1;
            } else {
                high = (int)mid - 1;
            }
        }
        return false;
    }

    // Solution2: A square number is 1+3+5+7+...
    // Time Complexity: O(sqrt(n))
    public boolean isPerfectSquare1(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    // Solution3: Newton Method
    public boolean isPerfectSquare2(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }
}
