package linked_list;

/* 142. Linked List Cycle II
Description:
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list,
we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

Follow up:
Can you solve it without using extra space?
 */

/* Solution:
using two pointers, one of them one step at a time. another pointer each take two steps
Suppose the first meet at step k, the length of the Cycle is r. so..2k-k=nr,k=nr
the distance between the start node of list and the start node of cycle is s.
the distance between the start of list and the first meeting node is k
(the pointer which wake one step at a time waked k steps)
the distance between the start node of cycle and the first meeting node is m,
so...s=k-m, s=nr-m=(n-1)r+(r-m),here we takes n = 1
so, using one pointer start from the start node of list,
another pointer start from the first meeting node,
all of them wake one step at a time, the first time they meeting each other is the start of the cycle.
*/

import common.ListNode;

public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode entry = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        return null;
    }

}
