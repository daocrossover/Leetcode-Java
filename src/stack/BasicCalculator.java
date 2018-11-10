package stack;

/* 224. Basic Calculator
Description:
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ),
the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:
Input: "1 + 1"
Output: 2

Example 2:
Input: " 2-1 + 2 "
Output: 3

Example 3:
Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23

Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */

import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        int num = 0, res = 0, sign = 1;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '+') {
                res += sign * num;
                sign = 1;
                num = 0;
            } else if (c == '-') {
                res += sign * num;
                sign = -1;
                num = 0;
            } else if (c == '(') {
                // push the result first, then sign;
                st.push(res);
                st.push(sign);
                // reset the sign and result for the value in the parenthesis
                sign = 1;
                res = 0;
            } else if (c == ')') {
                res += sign * num;
                num = 0;
                res *= st.pop(); // stack.pop() is the sign before the parenthesis
                res += st.pop(); // stack.pop() now is the result calculated before the parenthesis
            }
        }
        if (num != 0) {
            res += sign * num;
        }
        return res;
    }
}
