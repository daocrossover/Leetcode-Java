package linked_list;

import common.ListNode;

public class ReverseSecondHalfOfLinkedList {
    public static ListNode reverseSecondHalfList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode end = slow;
        while (fast != null && fast.next != null) {
            end = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            end = end.next;
            slow = slow.next;
        }
        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        end.next = prev;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 10; ++i) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        cur.next = null;
        head = reverseSecondHalfList(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
