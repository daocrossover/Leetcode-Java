package heap;

import common.ListNode;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 23. Merge k Sorted Lists
Description:
Merge k sorted linked lists and return it as one sorted list.
Analyze and describe its complexity.

Example:
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
 */

public class MergeKSortedLists {
    // Solution1: Min Heap
    // Time Complexity: O(N log k) where k is the number of linked lists.
    // The comparison cost is O(log k) for every pop and insertion to priority queue.
    // But finding the node with the smallest value just costs O(1) time.
    // There are N nodes in the final linked list.
    // Space Complexity:
    // 1. O(n) creating a new linked list costs O(n) space.
    // 2. O(k) the priority queue costs O(k) space.
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
        // PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // add the head of lists into the priority queue
        // the priority queue costs O(k) space
        for (ListNode head: lists) {
            if (head != null) {
                // need to check if the head is null
                pq.offer(head);
            }
        }
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }
        return dummy.next;
    }

    // Solution2: Divide and Conquer
    // Pair up k lists and merge each pair.
    // After the first pairing, k lists are merged into k/2 lists with average 2N/k length,
    // then k/4, k/8 and so on.
    // Repeat this procedure until we get the final sorted linked list.
    // Time Complexity: O(N log k)
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

    // D & C Iterative Solution, Space Complexity: O(1)
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int interval = 1;
        // reusing the input lists can save the space
        while (interval < lists.length) {
            for (int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    // D & C Recursive Solution, Space Complexity: O(log k)
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        } else if (left < right) {
            int mid = left + (right - left) / 2;
            ListNode l1 = merge(lists, left, mid);
            ListNode l2 = merge(lists, mid + 1, right);
            return mergeTwoLists(l1, l2);
        } else {
            return null;
        }
    }

    // Solution3: Merge lists one by one
    // Time Complexity: O(Nk)
    // Space Complexity: O(1)
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode head = mergeTwoLists(lists[0], lists[1]);
        for (int i = 2; i < lists.length; ++i) {
            head = mergeTwoLists(head, lists[i]);
        }
        return head;
    }
}
