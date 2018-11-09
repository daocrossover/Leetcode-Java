package linked_list;

/* 206. Reverse Linked List
Description:
Reverse a singly linked list.

Example:
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL

Follow up:
A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

import common.ListNode;

public class ReverseLinkedList {
    // Solution1: Iterative Solution
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // Solution2: Recursive Solution
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
}
