package math;

/* 504. Base 7
Description:
Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"

Example 2:
Input: -7
Output: "-10"

Note: The input will be in range of [-1e7, 1e7].
 */

public class Base7 {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean negative = false;
        if (num < 0) {
            negative = true;
            num = -num;
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (negative) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }
}
