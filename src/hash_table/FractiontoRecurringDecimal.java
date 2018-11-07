package hash_table;

/* 166. Fraction to Recurring Decimal
Description:
Given two integers representing the numerator and denominator of a fraction,
return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"

Example 3:
Input: numerator = 2, denominator = 3
Output: "0.(6)"
 */

import java.util.HashMap;

public class FractiontoRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // Handle the sign
        res.append((numerator > 0) ^ (denominator > 0) ? "-" : "");
        // Handle possible overflow using long, long
        // case like numerator = -2147483648, denominator = -1
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // integral part
        res.append(num / den);
        num %= den;
        // Handle cases without fractional part
        if (num == 0) {
            return res.toString();
        }

        // fractional part
        res.append(".");
        // For repeating fractional part,
        // use HashMap to store a remainder and its associated index
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            } else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}
