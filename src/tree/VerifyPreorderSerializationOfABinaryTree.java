package tree;

/* 331. Verify Preorder Serialization of a Binary Tree
Description:
One way to serialize a binary tree is to use pre-order traversal.
When we encounter a non-null node, we record the node's value.
If it is a null node, we record using a sentinel value such as #.
     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #

For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
where # represents a null node.

Given a string of comma separated values,
verify whether it is a correct preorder traversal serialization of a binary tree.
Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer
or a character '#' representing null pointer.

You may assume that the input format is always valid,
or example it could never contain two consecutive commas such as "1,,3".
 */

import java.util.Stack;

public class VerifyPreorderSerializationOfABinaryTree {
    // Solution1: Using a stack, scan left to right
    // case 1: we see a number, just push it to the stack
    // case 2: we see #, check if the top of stack is also #
    // if so, pop #, pop the number in a while loop, until top of stack is not #
    // if not, push it to stack
    // in the end, check if stack size is 1, and stack top is #
    // Time Complexity: O(n), Space Complexity: O(n)
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        Stack<String> st = new Stack<>();
        for (String node: nodes) {
            while (node.equals("#") && !st.isEmpty() && st.peek().equals(node)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
            st.push(node);
        }
        return st.peek().equals("#") && st.size() == 1;
    }

    // Solution2: In-degree and Out-degree Solution
    // all non-null node provides 2 out-degree and 1 in-degree (2 children and 1 parent), except root
    // all null node provides 0 out-degree and 1 in-degree (0 child and 1 parent).
    // During building, we record the difference between out degree and in degree
    // diff = out-degree - in-degree.
    // When the next node comes, we then decrease diff by 1, because the node provides an in-degree.
    // If the node is not null, we increase diff by 2, because it provides two out-degrees.
    // If a serialization is correct, diff should never be negative and diff will be zero when finished.
    // Time Complexity: O(n), Space Complexity: O(1)
    public boolean isValidSerialization1(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1; // root node add an in-degree
        for (String node: nodes) {
            if (--diff < 0) {
                return false;
            }
            if (!node.equals("#")) {
                diff += 2;
            }
        }
        return diff == 0;
    }
}
