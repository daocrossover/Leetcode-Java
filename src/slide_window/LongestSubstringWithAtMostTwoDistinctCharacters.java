package slide_window;

/* 159. Longest Substring with At Most Two Distinct Characters
Description:
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:
Input: "eceba"
Output: 3
Explanation: tis "ece" which its length is 3.

Example 2:
Input: "ccaabbb"
Output: 5

Explanation: tis "aabbb" which its length is 5.
 */

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // Write your code here
        char[] str = s.toCharArray();
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < str.length; ++j) {
            char cur = str[j];
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            while (map.size() > 2) {
                char left = str[i];
                if (map.containsKey(left)) {
                    map.put(left, map.get(left) - 1);
                    if (map.get(left) == 0) {
                        map.remove(left);
                    }
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
