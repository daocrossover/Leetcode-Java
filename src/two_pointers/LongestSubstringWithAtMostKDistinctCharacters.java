package two_pointers;

/* 340. Longest Substring with At Most K Distinct Characters
Description:
Given a string S, find the length of the longest substring T that contains at most k distinct characters.

Example 1:
Input: S = "eceba" and k = 3
Output: 4
Explanation: T = "eceb"

Example 2:
Input: S = "WORLD" and k = 4
Output: 4
Explanation: T = "WORL" or "ORLD"
 */

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0, res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.size() > k) {
                // shrink the window size
                char cl = s.charAt(left);
                map.put(cl, map.get(cl) - 1);
                if (map.get(cl) == 0) {
                    map.remove(cl);
                }
                left++;
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
    }
}
