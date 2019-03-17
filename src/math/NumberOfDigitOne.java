package math;

/* 233. Number of Digit One
Description:
Given an integer n, count the total number of digit 1
appearing in all non-negative integers less than or equal to n.

Example:
Input: 13
Output: 6
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */

/* 思路：
以21345为例。
步骤1：把1~21345的所有数字分成两段：第1段是1~1345，第2段是1346~21345。
步骤2：计算第2段中1出现的次数。
      步骤2.1：计算最高位万位中1出现的次数，要分最高位是否为1考虑。
              此处最高位大于1（21345），numFirstDigit = 10^4。
              否则（12345），numFirstDigit为去掉最高位数字之后剩余的数字加1（2345+1）
      步骤2.2：计算其他位中1出现的次数numOtherDigits = 2 * 10^3 * 4。
             （1346~21345与1~20000的numOtherDigits是相等的，所以可以转化为分析1~20000）
              将1346~21345分成两段，1346～11345和11346～21345，每一段有10000个数字。
              每一段剩下的4个数字中，选择一位为1，剩下3位为0-9，故总的个数为 2 * 4 * 10^3
步骤3：依据步骤1,2，递归计算1~1345。
 */

public class NumberOfDigitOne {
    public int countDigitOne(int n) {
        if (n <= 0) return 0;
        if (n < 10) return 1;
        String num = String.valueOf(n);
        char first = num.charAt(0);
        int len = num.length();
        String rest = num.substring(1);
        int numFirstDigit = 0;
        if (first > '1') {
            numFirstDigit = power10(len - 1);
        } else {
            numFirstDigit = Integer.parseInt(rest) + 1;
        }
        int numOtherDigits = (first - '0') * (len - 1) * power10(len - 2);
        return numFirstDigit + numOtherDigits + countDigitOne(Integer.parseInt(rest));
    }

    private int power10(int n) {
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= 10;
        }
        return res;
    }
}
