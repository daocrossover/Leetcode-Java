package string;

/* 522. Longest Uncommon Subsequence II
Description:
Given a list of strings, you need to find the longest uncommon subsequence among them.
The longest uncommon subsequence is defined as the longest subsequence of one of these strings
and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence
by deleting some characters without changing the order of the remaining elements.
Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence.
If the longest uncommon subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3

Note:
All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestUncommonSubsequenceII {
    // Solution1:
    // Sort the strings in the reverse order.
    // If there is not duplicates in the array, then the longest string is the answer.
    // But if there are duplicates, and if the longest string is not the answer,
    // then we need to check other strings. But the smaller strings can be subsequence of the bigger strings.
    // For this reason, we need to check if the string is a subsequence of all the strings bigger than itself.
    // If it's not, that is the answer.
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a, b) -> b.length() - a.length());
        Set<String> duplicate = getDuplicate(strs);
        for (int i = 0; i < strs.length; ++i) {
            if (!duplicate.contains(strs[i])) {
                if (i == 0) {
                    return strs[i].length();
                }
                for (int j = 0; j < i; ++j) {
                    if (isSubsequence(strs[j], strs[i])) {
                        break;
                    }
                    if (j == i - 1) {
                        return strs[i].length();
                    }
                }
            }
        }
        return -1;
    }

    private boolean isSubsequence(String a, String b) {
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == b.length();
    }

    private Set<String> getDuplicate(String[] strs) {
        Set<String> s = new HashSet<>();
        Set<String> duplicate = new HashSet<>();
        for (String str : strs) {
            if (s.contains(str)) {
                duplicate.add(str);
            }
            s.add(str);
        }
        return duplicate;
    }

    // Solution2: Brute Force
    public int findLUSlength1(String[] strs) {
        int res = -1, j = 0;
        int n = strs.length;
        for (int i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                if (isSubsequence(strs[j], strs[i])) {
                    break;
                }
            }
            if (j == n) {
                res = Math.max(res, strs[i].length());
            }
        }
        return res;
    }

}
