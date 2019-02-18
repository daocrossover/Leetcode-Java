package linked_list;

/* 25. Reverse Nodes in k-Group
Description:
Given a linked list, reverse the nodes of a linked list k at a time
and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5

Note:
Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
 */

import common.ListNode;

public class ReverseNodesInKGroup {
    // Solution1: Iterative Solution
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        while (len >= k) {
            cur = pre.next;
            for (int i = 1; i < k; ++i) {
                ListNode tmp = cur.next;
                cur.next = tmp.next;
                tmp.next = pre.next;
                pre.next = tmp;
            }
            pre = cur;
            len -= k;
        }
        return dummy.next;
    }

    // Solution2: Recursive Solution
    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        // find the k+1 node
        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }
        // if k+1 node is found
        if (count == k) {
            cur = reverseKGroup1(cur, k); // reverse list with k+1 node as head
            // head: head pointer to direct part
            // cur: head pointer to reversed part
            // reverse current k-group:
            while (count > 0) {
                ListNode tmp = head.next; // tmp: next head in direct part
                head.next = cur; // preappending "direct" head to the reversed list
                cur = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
                count--;
            }
            head = cur;
        }
        return head;
    }
}
