package linked_list;

/* 708.	Insert into a Cyclic Sorted List
Description:
Given a node from a cyclic linked list which is sorted in ascending order,
write a function to insert a value into the list such that it remains a cyclic sorted list.
The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value.
After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node.
Otherwise, you should return the original given node.
 */

import common.ListNode;

public class InsertIntoACyclicSortedList {
    public ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            ListNode node = new ListNode(insertVal);
            node.next = node;
            return node;
        }
        ListNode cur = head;
        do {
            if (insertVal >= cur.val && insertVal <= cur.next.val) break;
            if (cur.val > cur.next.val && (insertVal >= cur.val || insertVal <= cur.next.val)) break;
            cur = cur.next;
        } while (cur != head);
        ListNode node = new ListNode(insertVal);
        node.next = cur.next;
        cur.next = node;
        return head;
    }
}
