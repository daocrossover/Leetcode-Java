/* 151. Reverse Words in a String
Description:
Given an input string, reverse the string word by word.

Example:
Input: "the sky is blue",
Output: "blue is sky the".

Note:
1. A word is defined as a sequence of non-space characters.
2. Input string may contain leading or trailing spaces.
However, your reversed string should not contain leading or trailing spaces.
3. You need to reduce multiple spaces between two words to a single space in the reversed string.
 */

public class ReverseWordsInAString {
    // Using split():
    public String reverseWords(String s) {
        if (s.length() == 0 || s == null) {
            return "";
        }
        String[] parts = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = parts.length - 1; i >= 0; --i) {
            if (!parts[i].isEmpty()) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(parts[i]);
            }
        }
        return sb.toString();
    }
}
