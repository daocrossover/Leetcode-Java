package hash_table;

/* 500. Keyboard Row
Description:
Given a List of words, return the words that can be typed using letters of alphabet
on only one row's of American keyboard like the image below.

Example:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]

Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardRow {
    public String[] findWords(String[] words) {
        String[] rows = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
        Map<Character, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < rows.length; ++i) {
            for (char c : rows[i].toCharArray()) {
                map.put(c, i);
            }
        }
        for (String w : words) {
            int index = map.get(w.toUpperCase().charAt(0));
            for (char c: w.toUpperCase().toCharArray()) {
                if (map.get(c) != index) {
                    index = -1;
                    break;
                }
            }
            if (index != -1) {
                res.add(w);
            }
        }
        return res.toArray(new String[0]);
    }
}
