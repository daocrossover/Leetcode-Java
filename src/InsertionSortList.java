/* 147. Insertion Sort List
Sort a linked list using insertion sort.

Algorithm of Insertion Sort:
1. Insertion sort iterates, consuming one input element each repetition,
and growing a sorted output list.
2. At each iteration, insertion sort removes one element from the input data,
finds the location it belongs within the sorted list, and inserts it there.
3. It repeats until no input elements remain.

Example 1:
Input: 4->2->1->3
Output: 1->2->3->4

Example 2:
Input: -1->5->3->4->0
Output: -1->0->3->4->5
*/

import common.ListNode;

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode helper = new ListNode(0); // new starter of the sorted list
        ListNode cur = head; // the node will be inserted
        ListNode pre = helper; // insert node between pre and pre.next
        // not the end of input list
        while (cur != null) {
            ListNode next = cur.next;
            // find the right place to insert
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            // insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = helper;
            cur = next;
        }
        return helper.next;
    }
}
