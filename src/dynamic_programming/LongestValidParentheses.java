package dynamic_programming;

/* 32. Longest Valid Parentheses
Description:
Given a string containing just the characters '(' and ')',
find the length of the longest valid (well-formed) parentheses substring.

Example 1:
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"

Example 2:
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
 */

import java.util.Stack;

public class LongestValidParentheses {
    // Solution1: Dynamic Programming
    // dp[i]: the length of the longest valid substring ending at index i.
    // It's obvious that the valid substrings must end with ')'.
    // 1. If S[i] = ')' && S[i-1] = '(' -> S = "......()"
    // dp[i] = dp[i-2] + 2
    // 2. If S[i] = ')' && S[i-1] = ')' && S[i - dp[i-1] - 1] == '(' -> S = "......(,sub_s,)"
    // If the character before sub_s happens to be '(', we update the dp[i] as an addition of 2 in the length of sub_s which is dp[i−1].
    // To this, we also add the length of the valid substring just before the term "(,sub_s,)" , i.e. dp[i−dp[i−1]−2].
    // dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
    // Time Complexity: O(n), Space Complexity: O(n)
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i-1) == '(') {
                    dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
                } else if (i - dp[i-1] > 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                    dp[i] = dp[i-1] + (i - dp[i-1] >= 2 ? dp[i - dp[i-1] - 2] : 0) + 2;
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    // Solution2: Using Stack
    // For every '(' encountered, we push its index onto the stack.
    // For every ')' encountered, we pop the topmost element and subtract the current element's index from the top element of the stack,
    // which gives the length of the currently encountered valid string of parentheses.
    // If while popping the element, the stack becomes empty, we push the current element's index onto the stack.
    // Time Complexity: O(n), Space Complexity: O(n)
    public int longestValidParentheses1(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        // when s == "()", res = 1 - (-1) = 2
        stack.push(-1);
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    // Solution3: Two Traversal without Extra Space
    // Time Complexity: O(n), Space Complexity: O(1)
    public int longestValidParentheses2(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
