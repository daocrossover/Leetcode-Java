package slide_window;

/* 424. Longest Repeating Character Replacement
Description:
Given a string that consists of only uppercase English letters,
you can replace any letter in the string with another letter at most k times.
Find the length of a longest substring containing all repeating letters
you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:
Input:
s = "ABAB", k = 2
Output:
4
Explanation:
Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input:
s = "AABABBA", k = 1
Output:
4
Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 */

/* Solution
用start到end的滑动窗口，每次end滑动时统计读到的字符个数存到数组count中，
然后在窗口中判断，如果窗口长度 - 窗口中数量最多的字母数比k小，说明可以替换成功，计算当前窗口长度；
如果超过了k，则将窗口左端右移，并将窗口左端字母统计个数减1。
 */

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        int start = 0, maxLen = 0, maxCount = 0;
        int[] count = new int[26];
        for (int end = 0; end < len; ++end) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}
