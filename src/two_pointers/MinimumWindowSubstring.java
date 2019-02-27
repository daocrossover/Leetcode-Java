package two_pointers;

/* 76. Minimum Window Substring
Description:
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:
If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || s.length() < t.length()) {
            return "";
        }
        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0, minLeft = 0;
        int count = 0, minLen = s.length() + 1;
        for (int right = 0; right < s.length(); ++right) {
            char cur = s.charAt(right);
            if (map.containsKey(cur)) {
                map.put(cur, map.get(cur) - 1);
                if (map.get(cur) >= 0) {
                    count++;
                }
            }
            // move the right pointer until the window has all the elements from string t
            // record this desirable window
            while (left <= right && count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                // move the left pointer, if after deleting current left character,
                // the window does not contain all the elements from string t, count--
                if (map.containsKey(s.charAt(left))) {
                    map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
                    if (map.get(s.charAt(left)) > 0) {
                        count--;
                    }
                }
                left++;
            }
        }
        if (minLen > s.length()) {
            return "";
        }
        return s.substring(minLeft, minLeft + minLen);
    }
}
