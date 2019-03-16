package tree;

/* 255. Verify Preorder Sequence in Binary Search Tree
Description:
Given an array of numbers,
verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
 */

import java.util.Stack;

public class VerifyPreorderSequenceInBinarySearchTree {
    // Solution1: Using Stack
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> s = new Stack<>();
        // initialize current root as minimum possible value
        int low = Integer.MIN_VALUE;
        for (int i : preorder) {
            // If we find a node who is on right side
            // and smaller than root, return false
            if (i < low) return false;
            // If pre[i] is in right subtree of stack top,
            // Keep removing items smaller than pre[i]
            // and make the last removed item as new
            // root.
            while (!s.isEmpty() && i > s.peek()) {
                low = s.pop();
            }
            // At this point either stack is empty or
            // pre[i] is smaller than root, push pre[i]
            s.push(i);
        }
        return true;
    }

    // Solution2: Divide and Conquer
    public boolean verifyPreorder1(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean helper(int[] preorder, int start, int end, int lower, int upper) {
        if (start > end) return true;
        int val = preorder[start], i = 0;
        if (val <= lower || val >= upper) return false;
        for (i = start + 1; i <= end; ++i) {
            if (preorder[i] >= val) break;
        }
        return helper(preorder, start + 1, i - 1, lower, val) && helper(preorder, i, end, val, upper);
    }
}
