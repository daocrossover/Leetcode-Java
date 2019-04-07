package string;

/* 186. Reverse Words in a String II
Description:
Given an input character array, reverse the array word by word.
A word is defined as a sequence of non-space characters.

The input character array does not contain leading or trailing spaces
and the words are always separated by a single space.

Example:
Input: s = "the sky is blue"
Output: "blue is sky the"

Input: "a b c"
Output: "c b a"

Follow up:
Could you do it in-place without allocating extra space?
 */

public class ReverseWordsInAStringII {
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        // first reverse the whole string
        reverse(s, 0, s.length - 1);
        // then reverse each word
        for (int start = 0, i = 0; i <= s.length; i++) {
            if (i == s.length || s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
    }

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }
}
