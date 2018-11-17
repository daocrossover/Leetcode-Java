package hash_table;

/* 383. Ransom Note
Description:
Given an arbitrary ransom note string and another string containing letters from all the magazines,
write a function that will return true if the ransom note can be constructed from the magazines;
otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.
canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
 */

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    // Solution1: Using lookup table
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (int i = 0; i < magazine.length(); ++i) {
            count[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); ++i) {
            if (--count[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    // Solution2: Using HashMap
    public boolean canConstruct1(String ransomNote, String magazine) {
        Map<Character, Integer> magM = new HashMap<>();
        for (char c: magazine.toCharArray()) {
            int newCount = magM.getOrDefault(c, 0) + 1;
            magM.put(c, newCount);
        }
        for (char c: ransomNote.toCharArray()) {
            int newCount = magM.getOrDefault(c,0) - 1;
            if (newCount < 0) {
                return false;
            }
            magM.put(c, newCount);
        }
        return true;
    }
}
