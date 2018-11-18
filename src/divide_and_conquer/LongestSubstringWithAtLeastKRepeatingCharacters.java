package divide_and_conquer;

/* 395. Longest Substring with At Least K Repeating Characters
Description:
Find the length of the longest substring T of a given string (consists of lowercase letters only)
such that every character in T appears no less than k times.

Example 1:
Input:
s = "aaabb", k = 3
Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input:
s = "ababbc", k = 2
Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[26];
        // record the frequency of each character
        for (int i = 0; i < s.length(); ++i) {
            count[s.charAt(i) - 'a']++;
        }
        boolean flag = true;
        for (int c: count) {
            if (c < k && c > 0) {
                flag = false;
            }
        }
        // return the length of string if this string is a valid string
        if (flag) {
            return s.length();
        }
        int res = 0;
        int start = 0, cur = 0;
        // otherwise we use all the infrequent elements as splits
        while (cur < s.length()) {
            if (count[s.charAt(cur) - 'a'] < k) {
                res = Math.max(res, longestSubstring(s.substring(start, cur), k));
                start = cur + 1;
            }
            cur++;
        }
        res = Math.max(res, longestSubstring(s.substring(start), k));
        return res;
    }
}
