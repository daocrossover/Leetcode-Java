package slide_window;

/* 3. Longest Substring Without Repeating Characters
Description:
Given a string, find the length of the longest substring without repeating characters.

Example 1:
Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

/* Sliding Window Problem */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    // Solution1: Two Pointers and HashSet
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int i = 0, j = 0;
        int res = 0;
        char[] str = s.toCharArray();
        while (i < str.length && j < str.length) {
            if (!set.contains(str[j])) {
                set.add(str[j++]);
                res = Math.max(res, j - i);
            } else {
                set.remove(str[i++]);
            }
        }
        return res;
    }

    // Solution2: HashMap
    public int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] str = s.toCharArray();
        int res = 0;
        for (int i = 0, j = 0; j < str.length; ++j) {
            if (map.containsKey(str[j])) {
                i = Math.max(i, map.get(str[j]) + 1);
            }
            res = Math.max(res, j - i + 1);
            map.put(str[j], j);
        }
        return res;
    }

    // If we know that the charset is rather small,
    // we can replace the Map with an integer array as direct access table.
    // Commonly used tables are:
    // int[26] for Letters 'a' - 'z' or 'A' - 'Z'
    // int[128] for ASCII
    // int[256] for Extended ASCII

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
