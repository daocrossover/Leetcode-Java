package linked_list;

/* 138. Copy List with Random Pointer
Description:
A linked list is given such that each node contains an additional random pointer
which could point to any node in the list or null.

Return a deep copy of the list.

Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.

Note:
You must return the copy of the given head as a reference to the cloned list.
 */

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    // Definition for a Node.
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    // Solution1: Associate the original node with its copy node in a single linked list.
    // Time Complexity: O(n), Space Complexity: O(1)
    public Node copyRandomList(Node head) {
        // 1. iterate the original list and duplicate each node. The duplicate
        // of each node follows its original immediately.
        Node cur = head, next;
        while (cur != null) {
            next = cur.next;
            Node copy = new Node(cur.val);
            cur.next = copy;
            copy.next = next;
            cur = next;
        }

        // 2. iterate the new list and assign the random pointer for each
        // duplicated node.
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                // cur.random.next is the copied node which is pointed by random
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 3. restore the original list and extract the duplicated nodes.
        Node dummy = new Node(0);
        Node copyCur = dummy, copy;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            // extract the copy
            copy = cur.next;
            copyCur.next = copy;
            copyCur = copy;
            // restore the original list
            cur.next = next;
            cur = next;
        }
        return dummy.next;
    }

    // Solution2: Using HashMap to store the original node
    // Time Complexity: O(n), Space Complexity: O(n)
    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
}
