package string;

/* 459. Repeated Substring Pattern
Description:
Given a non-empty string check if it can be constructed by taking a substring of it and
appending multiple copies of the substring together.
You may assume the given string consists of lowercase English letters only and
its length will not exceed 10000.

Example 1:
Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.

Example 2:
Input: "aba"
Output: False

Example 3:
Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */

public class RepeatedSubstringPattern {
    // Solution1:
    // 1. First char of input string is first char of repeated substring
    // 2. Last char of input string is last char of repeated substring
    // 3. Let S1 = S + S (where S in input string)
    // 4. Remove 1 and last char of S1. Let this be S2
    // 5. If S exists in S2 then return true else false
    // 6. Let i be index in S2 where S starts then repeated substring length i + 1 and
    // repeated substring S[0: i+1]
    public boolean repeatedSubstringPattern(String s) {
        String s1 = s + s;
        return s1.substring(1, s1.length() - 1).contains(s);
    }

    // Solution2: the length of substring <= s.length() / 2
    // Time Complexity: O(n^2)
    public boolean repeatedSubstringPattern1(String s) {
        int len = s.length();
        for (int i = len / 2; i >= 1; --i) {
            if (len % i == 0) {
                int m = len / i;
                String sub = s.substring(0, i);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; ++j) {
                    sb.append(sub);
                }
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}
