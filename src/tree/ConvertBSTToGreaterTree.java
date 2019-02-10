package tree;

/* 538. Convert BST to Greater Tree
Description:
Given a Binary Search Tree (BST), convert it to a Greater Tree such that
every key of the original BST is changed to the original key
plus sum of all keys greater than the original key in BST.

Example:
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
 */

import common.TreeNode;
import java.util.Stack;

public class ConvertBSTToGreaterTree {
    // Solution1: Recursive
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        int tmp = root.val;
        root.val += sum;
        sum += tmp;
        convertBST(root.left);
        return root;
    }

    // Solution2: Iterative
    public TreeNode convertBST1(TreeNode root) {
        int sum = 0;
        Stack<TreeNode> s = new Stack<>();
        TreeNode node = root;
        while (!s.isEmpty() || node != null) {
            while (node != null) {
                s.push(node);
                node = node.right;
            }
            node = s.pop();
            sum += node.val;
            node.val = sum;
            node = node.left;
        }
        return root;
    }
}
