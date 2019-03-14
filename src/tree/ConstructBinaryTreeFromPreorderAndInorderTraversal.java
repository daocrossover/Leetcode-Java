package tree;

/* 105. Construct Binary Tree from Preorder and Inorder Traversal
Description:
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:
    3
   / \
  9  20
    /  \
   15   7
 */

import common.TreeNode;
import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return build1(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    // Solution1: just find preorder[preLeft] in the inorder array
    // and recursively construct the left subtree and right subtree
    private TreeNode build(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) return null;
        TreeNode root = new TreeNode(preorder[preLeft]);
        int i;
        for (i = inLeft; i <= inRight; ++i) {
            if (inorder[i] == root.val) {
                break;
            }
        }
        root.left = build(preorder, inorder, preLeft + 1, preLeft + i - inLeft, inLeft, i - 1);
        root.right = build(preorder, inorder, preLeft + i - inLeft + 1, preRight, i + 1, inRight);
        return root;
    }

    // Solution2: use a HashMap to store the index of inorder array
    private TreeNode build1(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) return null;
        TreeNode root = new TreeNode(preorder[preLeft]);
        int i = map.get(preorder[preLeft]);
        root.left = build(preorder, inorder, preLeft + 1, preLeft + i - inLeft, inLeft, i - 1);
        root.right = build(preorder, inorder, preLeft + i - inLeft + 1, preRight, i + 1, inRight);
        return root;
    }
}
