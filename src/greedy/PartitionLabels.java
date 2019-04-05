package greedy;

/* 763. Partition Labels
Description:
A string S of lowercase letters is given. We want to partition this string into as many parts as possible
so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

Note:
S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartitionLabels {
    // Solution1: Greedy + Two Pointers + HashMap
    public List<Integer> partitionLabels(String S) {
        ArrayList<Integer> res = new ArrayList<>();
        int[] last = new int[26];
        // HashMap stores the last index of the character
        for (int i = 0; i < S.length(); ++i) {
            last[S.charAt(i) - 'a'] = i;
        }
        int anchor = 0, j = 0;
        for (int i = 0; i < S.length(); ++i) {
            // greedy, if the current index is the last index of the current character
            // then split
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                res.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return res;
    }

    // Solution2: Greedy + Two Pointers + HashMap + HashSet
    public List<Integer> partitionLabels1(String S) {
        ArrayList<Integer> res = new ArrayList<>();
        int[] count = new int[26];
        char[] s = S.toCharArray();
        for (char c: s) {
            count[c - 'a']++;
        }
        int preIndex = -1;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length; ++i) {
            count[s[i] - 'a']--;
            if (count[s[i] - 'a'] == 0) {
                boolean split = true;
                for (char c: set) {
                    if (count[c - 'a'] != 0) {
                        split = false;
                    }
                }
                if (split) {
                    res.add(i - preIndex);
                    preIndex = i;
                    set.clear();
                }
            } else {
                set.add(s[i]);
            }
        }
        return res;
    }
}
