package string;

/* 557. Reverse Words in a String III
Description:
Given a string, you need to reverse the order of characters in each word
within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space
and there will not be any extra space in the string.
 */

public class ReverseWordsInAStringIII {
    // Solution1: Using built-in reverse
    public String reverseWords(String s) {
        String[] str = s.split(" ");
        for (int i = 0; i < str.length; i++) {
            str[i] = new StringBuilder(str[i]).reverse().toString();
        }
        StringBuilder res = new StringBuilder();
        for (String st : str) {
            res.append(st);
            res.append(" ");
        }
        return res.toString().trim();
    }

    // Solution2:
    public String reverseWords1(String s) {
        String[] strs = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(reverse(str));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    private String reverse(String s) {
        char[] a = s.toCharArray();
        int i = 0, j = a.length - 1;
        while (i < j) {
            char tmp = a[i];
            a[i++] = a[j];
            a[j--] = tmp;
        }
        return new String(a);
    }
}
