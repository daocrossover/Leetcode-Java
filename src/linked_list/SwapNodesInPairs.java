package linked_list;

/* 24. Swap Nodes in Pairs
Description:
Given a linked list, swap every two adjacent nodes and return its head.
You may not modify the values in the list's nodes, only nodes itself may be changed.

Example:
Given 1->2->3->4, you should return the list as 2->1->4->3.
 */

import common.ListNode;

public class SwapNodesInPairs {
    // Solution1: Iterative Solution
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = dummy.next;
        while (cur != null && cur.next != null) {
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = cur;
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    // Solution2: Recursive Solution
    public ListNode swapPairs1(ListNode head) {
        ListNode cur = head;
        if (cur != null && cur.next != null) {
            cur = head.next;
            head.next = swapPairs1(cur.next);
            cur.next = head;
        }
        return cur;
    }
}
