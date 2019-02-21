package hash_table;

/* 49. Group Anagrams
Description:
Given an array of strings, group anagrams together.

Example:
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:
All inputs will be in lowercase.
The order of your output does not matter.
 */

import java.util.*;

public class GroupAnagrams {
    // maintain a map : {String -> List} where each key K is a sorted string,
    // and each value is the list of strings from the initial input that when sorted,
    // are equal to K.
    // Time Complexity: O(nklogk), where n is the length of strs,
    // and k is the maximum length of a string in strs.
    // Space Complexity: O(nk)
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    // if the string only has the 26 letters
    // turn the key to the string like #1#2#3#0#0#0...#0(abbccc)
    // Time Complexity: O(nk), where n is the length of strs,
    // and k is the maximum length of a string in strs.
    // Space Complexity: O(nk)
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; ++i) {
                sb.append("#");
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

}
