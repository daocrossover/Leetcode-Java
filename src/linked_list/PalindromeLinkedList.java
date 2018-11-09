package linked_list;

/* 234. Palindrome Linked List
Description:
Given a singly linked list, determine if it is a palindrome.

Example 1:
Input: 1->2
Output: false

Example 2:
Input: 1->2->2->1
Output: true

Follow up:
Could you do it in O(n) time and O(1) space?
 */

import common.ListNode;

public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        // 1. Move: fast pointer goes to the end, and slow goes to the middle.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        // 2. Reverse: the right half is reversed, and slow pointer becomes the 2nd head.
        slow = reverse(slow);
        // 3. Compare: run the two pointers head and slow together and compare.
        fast = head;
        while (slow != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
