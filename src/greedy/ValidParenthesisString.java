package greedy;

/* 678. Valid Parenthesis String
Description:
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid.
We define the validity of a string by these rules:
1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
4. '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
5. An empty string is also valid.

Example 1:
Input: "()"
Output: True

Example 2:
Input: "(*)"
Output: True

Example 3:
Input: "(*))"
Output: True

Note:
The string size will be in the range [1, 100].
 */

public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        // min_left_op: smallest possible number of open left brackets
        // max_left_op: largest possible number of open left brackets
        int min_left_op = 0, max_left_op = 0;
        for (char c: s.toCharArray()) {
            if (c == '(') {
                min_left_op++;
                max_left_op++;
            } else if (c == ')') {
                min_left_op--;
                max_left_op--;
            } else {
                min_left_op--;
                max_left_op++;
            }
            // never have less than 0 open left brackets
            if (max_left_op < 0) {
                return false;
            }
            // always be valid (if min_left_op > 0, also invalid)
            min_left_op = Math.max(min_left_op, 0);
        }
        return min_left_op == 0;
    }
}
