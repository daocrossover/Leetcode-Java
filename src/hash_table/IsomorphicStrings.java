package hash_table;

/* 205. Isomorphic Strings
Description:
Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true

Note:
You may assume both s and t have the same length.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsomorphicStrings {
    // Solution1: Using HashMap
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); ++i) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (!map.containsKey(a)) {
                // s = "ab", t = "aa", return false
                if (!map.containsValue(b)) {
                    map.put(a, b);
                } else {
                    return false;
                }
            } else {
                if (!map.get(a).equals(b)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Solution2: Using HashMap + HashSet
    public boolean isIsomorphic1(String s, String t) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        Set set = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (!map.containsKey(a)) {
                if (!set.contains(b)) {
                    map.put(a, b);
                    set.add(b);
                } else {
                    return false;
                }
            } else {
                if (!map.get(a).equals(b)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Solution3: Using char arrays as lookup tables
    // Since all the test cases use ASCII characters,
    // you can use small arrays as lookup tables.
    public boolean isIsomorphic2(String s, String t) {
        char[] sm = new char[256];
        char[] tm = new char[256];
        for (int i = 0; i < s.length(); ++i) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (sm[sc] == 0 && tm[tc] == 0) {
                sm[sc] = tc;
                tm[tc] = sc;
            } else {
                if(sm[sc] != tc || tm[tc] != sc){
                    return false;
                }
            }
        }
        return true;
    }
}
