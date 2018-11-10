package stack;

/* 227. Basic Calculator II
Description:
Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces.
The integer division should truncate toward zero.

Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5

Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */

import java.util.Stack;

public class BasicCalculatorII {
    // Solution1: Using Stack
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Integer> st = new Stack<>();
        int num = 0, len = s.length();
        char op = '+';
        for (int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == len - 1) {
                switch (op) {
                    case '+':
                        st.push(num);
                        break;
                    case '-':
                        st.push(-num);
                        break;
                    case '*':
                        st.push(st.pop() * num);
                        break;
                    case '/':
                        st.push(st.pop() / num);
                        break;
                }
                num = 0;
                op = c;
            }
        }

        int res = 0;
        for (int i: st) {
            res += i;
        }
        return res;
    }

    // Solution2: Without stack
    public int calculate1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int curRes = 0, num = 0, res = 0;
        int len = s.length();
        char op = '+';
        for (int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == len - 1) {
                switch (op) {
                    case '+':
                        curRes += num;
                        break;
                    case '-':
                        curRes -= num;
                        break;
                    case '*':
                        curRes *= num;
                        break;
                    case '/':
                        curRes /= num;
                        break;
                }
                if (c == '+' || c == '-' || i == len - 1) {
                    res += curRes;
                    curRes = 0;
                }
                num = 0;
                op = c;
            }
        }
        return res;
    }
}
