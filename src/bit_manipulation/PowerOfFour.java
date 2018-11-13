package bit_manipulation;

/* 342. Power of Four
Description:
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example 1:
Input: 16
Output: true

Example 2:
Input: 5
Output: false

Follow up: Could you solve it without loops/recursion?
 */

public class PowerOfFour {
    // Solution1: Bit operation
    // 0x55555555 is to get rid of those power of 2 but not power of 4
    // so that the single 1 bit always appears at the odd position
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num-1)) == 0 && (num & 0x55555555) != 0;
    }

    // Solution2: Bit operation
    public boolean isPowerOfFour1(int num) {
        return num > 0 && (num & (num-1)) == 0 && (num - 1)%3 == 0;
    }
}
