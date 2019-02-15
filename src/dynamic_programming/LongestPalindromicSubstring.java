package dynamic_programming;

/* 5. Longest Palindromic Substring
Description:
Given a string s, find the longest palindromic substring in s.
You may assume that the maximum length of s is 1000.

Example 1:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:
Input: "cbbd"
Output: "bb"
 */

public class LongestPalindromicSubstring {
    // Solution1: Expand Around Center
    // observe that a palindrome mirrors around its center.
    // Therefore, a palindrome can be expanded from its center,
    // and there are only 2n - 1 such centers.
    // Time Complexity: O(n^2), Space Complexity: O(1)
    private int start = 0, end = 0;
    private int maxLen = 0;
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        for (int i = 0; i < s.length() - 1; ++i) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i + 1);
        }
        return s.substring(start, end);
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (maxLen < right - left - 1) {
            start = left + 1;
            end = right;
            maxLen = right - left - 1;
        }
    }

    // Solution2: Dynamic Programming
    // dp[i][j] = true, if s[i, j] is a palindrome
    //          = false, otherwise
    // dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
    // dp[i][i] = true
    // dp[i][i+1] = s[i] == s[i+1]
    // Time Complexity: O(n^2), Space Complexity: O(n^2)
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int start = 0, end = 0;
        for (int i = len - 1; i >= 0; --i) {
            for (int j = i; j < len; ++j) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i+1][j-1]);
                if (dp[i][j] && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
