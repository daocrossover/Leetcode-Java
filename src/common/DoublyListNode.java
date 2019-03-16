package common;

public class DoublyListNode {
    public int val;
    public DoublyListNode next;
    public DoublyListNode prev;

    public DoublyListNode() {}

    public DoublyListNode(int val) {
        this.val = val;
        this.next = this.prev = null;
    }

    public DoublyListNode(int _val, DoublyListNode _next, DoublyListNode _prev) {
        val = _val;
        next = _next;
        prev = _prev;
    }
}
