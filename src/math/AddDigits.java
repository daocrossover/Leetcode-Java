package math;

/* 258. Add Digits
Description:
Given a non-negative integer num,
repeatedly add all its digits until the result has only one digit.

Example:
Input: 38
Output: 2
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
             Since 2 has only one digit, return it.
Follow up:
Could you do it without any loop/recursion in O(1) runtime?
 */

public class AddDigits {
    // Solution1: Math Solution:
    public int addDigits(int num) {
        return 1 + (num - 1) % 9;
    }

    // Solution2: Traditional Solution:
    public int addDigits1(int num) {
        if (num == 0) return 0;
        int sum = 0;
        while (true) {
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            if (sum / 10 == 0) {
                return sum;
            }
            num = sum;
            sum = 0;
        }
    }
}
