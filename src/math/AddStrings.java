package math;

/* 415. Add Strings
Description:
Given two non-negative integers num1 and num2 represented as string,
return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

public class AddStrings {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int idx1 = num1.length() - 1;
        int idx2 = num2.length() - 1;
        int sum = 0;
        while (idx1 >= 0 || idx2 >= 0) {
            sum += idx1 >= 0 ? (num1.charAt(idx1) - '0') : 0;
            sum += idx2 >= 0 ? (num2.charAt(idx2) - '0') : 0;
            idx1--;
            idx2--;
            sb.append(sum % 10);
            sum /= 10;
        }
        if (sum != 0) {
            sb.append(sum);
        }
        return sb.reverse().toString();
    }
}
