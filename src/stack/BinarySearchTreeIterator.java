package stack;

/* 173. Binary Search Tree Iterator
Description:
Implement an iterator over a binary search tree (BST).
Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory,
where h is the height of the tree.
 */

/* 思路
实际考察的是BST的非递归中序遍历，BST中序遍历可以得到递增序列
但是要求O(h)的空间复杂度，实际上将中序遍历拆开，
可以在构造器中首先将根结点所有左子链入栈，然后在next()函数访问当前节点时，
将当前节点的右儿子的所有左子链入栈。
 */

import common.TreeNode;
import java.util.Stack;

public class BinarySearchTreeIterator {
    public class BSTIterator {
        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            pushAll(root);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            pushAll(node.right);
            return node.val;
        }

        private void pushAll(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    /**
     * Your BSTIterator will be called like this:
     * BSTIterator i = new BSTIterator(root);
     * while (i.hasNext()) v[f()] = i.next();
     */
}
