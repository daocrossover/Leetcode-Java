package linked_list;

/* 430. Flatten a Multilevel Doubly Linked List
Description:
You are given a doubly linked list which in addition to the next and previous pointers,
it could have a child pointer, which may or may not point to a separate doubly linked list.
These child lists may have one or more children of their own, and so on,
to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list.
You are given the head of the first level of the list.

Example:

Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
 */


// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
}

public class FlattenAMultilevelDoublyLinkedList {
    // Solution1: Recursive
    public Node flatten(Node head) {
        flattenTail(head);
        return head;
    }

    private Node flattenTail(Node head) {
        if (head == null) {
            return null;
        }
        if (head.child == null) {
            if (head.next == null) {
                return head;
            }
            return flattenTail(head.next);
        } else {
            Node child = head.child;
            head.child = null;
            Node next = head.next;
            Node childTail = flattenTail(child);
            head.next = child;
            child.prev = head;
            if (next != null) {
                childTail.next = next;
                next.prev = childTail;
                return flattenTail(next);
            }
            return childTail;
        }
    }

    // Solution2: Iterative
    public Node flatten1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            // case 1: if no child, proceed
            if (cur.child == null) {
                cur = cur.next;
                continue;
            }
            // case 2: got child, find the tail of the child and link it to p.next
            Node tmp = cur.child;
            // find the tail of the child
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            // connect tail with p.next, if it is not null
            tmp.next = cur.next;
            if (cur.next != null) {
                cur.next.prev = tmp;
            }
            // connect p with p.child, and remove p.child
            cur.next = cur.child;
            cur.child.prev = cur;
            cur.child = null;
        }
        return head;
    }
}
