package two_pointers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 345. Reverse Vowels of a String
Description:
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Input: "hello"
Output: "holle"

Example 2:
Input: "leetcode"
Output: "leotcede"

Note:
The vowels does not include the letter "y".
 */

public class ReverseVowelsOfAString {
    public String reverseVowels(String s) {
        int start = 0, end = s.length() - 1;
        char[] chars = s.toCharArray();
        Set<Character> st = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        while (start < end) {
            while (start < end && !st.contains(chars[start])) {
                start++;
            }
            while (start < end && !st.contains(chars[end])) {
                end--;
            }
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
        return new String(chars);
    }
}
