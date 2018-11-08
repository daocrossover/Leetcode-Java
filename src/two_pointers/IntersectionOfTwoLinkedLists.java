package two_pointers;

/* 160. Intersection of Two Linked Lists
Description:
Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3
begin to intersect at node c1.

Notes:
1. If the two linked lists have no intersection at all, return null.
2. The linked lists must retain their original structure after the function returns.
3. You may assume there are no cycles anywhere in the entire linked structure.
4. Your code should preferably run in O(n) time and use only O(1) memory.

Solution:
Using two pointers

Analysis:
Time Complexity: O(m+n), Space Complexity: O(1)
 */

import common.ListNode;

public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // boundary check
        if(headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        // if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            // for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
