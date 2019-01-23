package dynamic_programming;

/* 467. Unique Substrings in Wraparound String
Description:
Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz",
so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Now we have another string p.
Your job is to find out how many unique non-empty substrings of p are present in s.
In particular, your input is the string p and you need to output the number of
different non-empty substrings of p in the string s.

Note: p consists of only lowercase English letters and the size of p might be over 10000.

Example 1:
Input: "a"
Output: 1
Explanation: Only the substring "a" of string "a" is in the string s.

Example 2:
Input: "cac"
Output: 2
Explanation: There are two substrings "a", "c" of string "cac" in the string s.

Example 3:
Input: "zab"
Output: 6
Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 */

public class UniqueSubstringsInWraparoundString {
    public int findSubstringInWraproundString(String p) {
        char[] chars = p.toCharArray();
        // count[i] is the maximum unique substring end with ith letter.
        // 0 - 'a', 1 - 'b', ..., 25 - 'z'.
        int[] count = new int[26];
        // store longest contiguous substring ends at current position.
        int maxLen = 0;
        for (int i = 0; i < chars.length; ++i) {
            if (i > 0 && (chars[i] == chars[i-1] + 1 || chars[i] == chars[i-1] - 25)) {
                maxLen++;
            } else {
                maxLen = 1;
            }
            int index = chars[i] - 'a';
            count[index] = Math.max(maxLen, count[index]);
        }
        // sum to get result
        int res = 0;
        for (int c : count) {
            res += c;
        }
        return res;
    }
}
