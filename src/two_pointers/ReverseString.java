package two_pointers;

/* 344. Reverse String
Description:
Write a function that takes a string as input and returns the string reversed.

Example 1:
Input: "hello"
Output: "olleh"

Example 2:
Input: "A man, a plan, a canal: Panama"
Output: "amanaP :lanac a ,nalp a ,nam A"
 */

public class ReverseString {
    public String reverseString(String s) {
        char[] chars = s.toCharArray();
        int start = 0, end = s.length() - 1;
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
        return new String(chars);
    }
}
