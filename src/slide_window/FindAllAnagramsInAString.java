package slide_window;

/* 438. Find All Anagrams in a String
Description:
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and
p will not be larger than 20,100.

The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) {
            return res;
        }
        // create a HashMap to save the Characters of the target substring.
        // (K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // maintain a counter to check whether match the target string.
        int counter = map.size(); // must be the map size, NOT the string size because the char may be duplicate.
        int start = 0, end = 0;
        // the length of the substring which match the target string.
        int len = Integer.MAX_VALUE;
        // loop at the beginning of the source string
        while (end < s.length()) {
            char c = s.charAt(end); // get a character
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1); // plus or minus one
                if (map.get(c) == 0) {
                    counter--; // modify the counter according the requirement(different condition).
                }
            }
            end++;
            // increase begin pointer to make it invalid/valid again
            while (counter == 0) { // counter condition. different question may have different condition
                char temp = s.charAt(start);
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1); // plus or minus one
                    if(map.get(temp) > 0) counter++; // modify the counter according the requirement(different condition).
                }
                /* save / update(min/max) the result if find a target*/
                // result collections or result int value
                if (end - start == p.length()){
                    res.add(start);
                }
                start++;
            }
        }
        return res;
    }
}
