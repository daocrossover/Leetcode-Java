package stack;

/* 316. Remove Duplicate Letters
Description:
Given a string which contains only lowercase letters,
remove duplicate letters so that every letter appear once and only once.
You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:
Input: "bcabc"
Output: "abc"

Example 2:
Input: "cbacdcbc"
Output: "acdb"
 */

import java.util.Stack;

public class RemoveDuplicateLetters {
    // Solution1: Using Stack
    public String removeDuplicateLetters(String s) {
        // count[] will contain number of occurrences of character (i+'a')
        int[] count = new int[26];
        // visited[] will contain if character (i+'a') is present in current result Stack
        boolean[] visited = new boolean[26];
        char[] chars = s.toCharArray();
        for (char c: chars) { // count number of occurrences of character
            count[c - 'a']++;
        }
        Stack<Character> st = new Stack<>(); // answer stack
        int index;
        for (char c: chars) {
            index= c - 'a';
            count[index]--; // decrement number of characters remaining in the string to be analysed
            if (visited[index]) { // if character is already present in stack, don't bother
                continue;
            }
            // if current character is smaller than last character in stack
            // which occurs later in the string again
            // it can be removed and added later
            // e.g stack = bc, remaining string abc then a can pop b and then c
            while (!st.isEmpty() && c < st.peek() && count[st.peek()-'a'] != 0) {
                visited[st.pop() - 'a'] = false;
            }
            st.push(c); // add current character and mark it as visited
            visited[index] = true;
        }

        StringBuilder sb = new StringBuilder();
        // pop character from stack and build answer string from back
        while (!st.isEmpty()) {
            sb.insert(0,st.pop());
        }
        return sb.toString();
    }

    // Solution2: Greedy Solution
    // First count all the characters in the string,
    // and then when iterate the string, when see a smaller char than the previous one,
    // greedily remove the previous ones as long as there are still same chars later.
    // A visited array is used to make sure than we only take same character to the left
    // because we want the lexi smallest sequence.
    public String removeDuplicateLetters1(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c: chars) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[26];
        for (char c: chars) {
            count[c - 'a']--;
            if (visited[c - 'a']) {
                continue;
            }
            while (sb.length() > 0 && c <= sb.charAt(sb.length() - 1) && count[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                visited[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(c);
            visited[c - 'a'] = true;
        }
        return sb.toString();
    }
}
