package hash_table;

/* 242. Valid Anagram
Description:
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    // Solution1: Hash Table
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            nums[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); ++i) {
            if (--nums[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    // Solution2: Sorting
    // Time Complexity: O(n log n)
    // Space Complexity: O(1)
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    // Solution3: Solution to Follow up Using HashMap
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> dict = new HashMap<>();
        for (char c: s.toCharArray()) {
            dict.put(c, dict.getOrDefault(c, 0) + 1);
        }
        for (char c: t.toCharArray()) {
            dict.put(c, dict.getOrDefault(c, 0) - 1);
            if (dict.get(c) < 0) {
                return false;
            }
        }
        return true;
    }
}
