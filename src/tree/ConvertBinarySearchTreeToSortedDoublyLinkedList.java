package tree;

/* 426. Convert Binary Search Tree to Sorted Doubly Linked List
Description:
Convert a BST to a sorted circular doubly-linked list in-place.
Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

We want to transform this BST into a circular doubly linked list.
Each node in a doubly linked list has a predecessor and successor.
For a circular doubly linked list, the predecessor of the first element is the last element,
and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above.
The "head" symbol means the node it points to is the smallest element of the linked list.

Specifically, we want to do the transformation in place.
After the transformation, the left pointer of the tree node should point to its predecessor,
and the right pointer should point to its successor.
We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST.
The solid line indicates the successor relationship,
while the dashed line means the predecessor relationship.
 */

import common.DoublyListNode;
import common.TreeNode;
import java.util.Stack;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    // Solution1: Inorder Traversal
    public DoublyListNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        DoublyListNode head = null;
        DoublyListNode pre = null;
        Stack<TreeNode> s = new Stack<>();
        TreeNode node = root;
        while (!s.isEmpty() || node != null) {
            // to the leftmost node
            while (node != null) {
                s.push(node);
                node = node.left;
            }
            node = s.pop();
            DoublyListNode cur = new DoublyListNode(node.val);
            if (head == null) head = cur; // if head is null, the cur is the leftmost node
            if (pre != null) {
                pre.next = cur;
                cur.prev = pre;
            }
            pre = cur;
            node = node.right;
        }
        pre.next = head;
        head.prev = pre;
        return head;
    }

    // Solution2: Divide and Conquer
    public DoublyListNode treeToDoublyList1(TreeNode root) {
        // write your code here
        if (root == null) return null;
        DoublyListNode leftHead = treeToDoublyList(root.left);
        DoublyListNode rightHead = treeToDoublyList(root.right);
        DoublyListNode rootNode = new DoublyListNode(root.val);
        rootNode.prev = rootNode;
        rootNode.next = rootNode;
        return connect(connect(leftHead, rootNode), rightHead);
    }

    private DoublyListNode connect(DoublyListNode node1, DoublyListNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        DoublyListNode tail1 = node1.prev, tail2 = node2.prev;
        tail1.next = node2;
        node2.prev = tail1;
        tail2.next = node1;
        node1.prev = tail2;
        return node1;
    }
}
