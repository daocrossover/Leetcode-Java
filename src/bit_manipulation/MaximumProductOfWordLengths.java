package bit_manipulation;

/* 318. Maximum Product of Word Lengths
Description:
Given a string array words, find the maximum value of length(word[i]) * length(word[j])
where the two words do not share common letters.
You may assume that each word will contain only lower case letters.
If no such two words exist, return 0.

Example 1:
Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".

Example 2:
Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".

Example 3:
Input: ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 */

public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int[] bits = new int[words.length];
        // turn every word to the bit map
        // e.g. "abcd" = 00000000000000000000001111 = 15
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            for (int j = 0; j < w.length(); ++j) {
                bits[i] |= 1 << (w.charAt(j) - 'a');
            }
        }

        int res = 0;
        for (int i = 0; i < words.length; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                // if bits[i] & bits[j] == 0, then two words do not share common letters.
                if ((bits[i] & bits[j]) == 0 && words[i].length() * words[j].length() > res) {
                    res =words[i].length() * words[j].length();
                }
            }
        }
        return res;
    }
}
