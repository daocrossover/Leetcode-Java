package slide_window;

/* 340. Longest Substring with At Most K Distinct Characters
Description:
Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,
T is "ece" which its length is 3.
 */

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // Write your code here
        char[] str = s.toCharArray();
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < str.length; ++j) {
            char cur = str[j];
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            while (map.size() > k) {
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
