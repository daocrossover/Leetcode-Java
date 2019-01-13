package trie;

/* 421. Maximum XOR of Two Numbers in an Array
Description:
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:
Input: [3, 10, 5, 25, 2, 8]
Output: 28
Explanation: The maximum result is 5 ^ 25 = 28.
 */

class Trie {
    Trie[] children;
    public Trie() {
        children = new Trie[2];
    }
}

public class MaximumXOROfTwoNumbersInAnArray {
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Trie root = new Trie();
        // build the trie tree
        for (int num : nums) {
            Trie curNode = root;
            for (int i = 31; i >= 0; --i) {
                int curBit = (num >>> i) & 1;
                if (curNode.children[curBit] == null) {
                    curNode.children[curBit] = new Trie();
                }
                curNode = curNode.children[curBit];
            }
        }
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            Trie curNode = root;
            int sum = 0;
            for (int i = 31; i >= 0; --i) {
                int curBit = (num >>> i) & 1;
                // trying to find a different bit on each digit position
                // to make the value of XOR the max
                if (curNode.children[curBit ^ 1] != null) {
                    sum += (1 << i);
                    curNode = curNode.children[curBit ^ 1];
                } else {
                    curNode = curNode.children[curBit];
                }
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
