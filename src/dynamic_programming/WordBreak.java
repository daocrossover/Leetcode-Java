package dynamic_programming;

/* 139. Word Break
Description:
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> wordSet = new HashSet<>(wordDict);
        // dp[i]: if dp[i] is true, then s[0, i-1] is a valid word made of words in dictionary.
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                String sub = s.substring(j, i);
                // if the word s[0, j–1] is a valid word
                // and sub is in the set, s[0, i-1] is a valid word
                if (dp[j] && wordSet.contains(sub)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
