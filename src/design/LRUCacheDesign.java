package design;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheDesign {
    // doubly-linked list data structure
    private class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        Node() {
            this(0, 0);
        }
    }

    // LRU uses HashMap and doubly-linked list
    private Map<Integer, Node> map;
    private Node head, tail;
    private int capacity, count;

    public LRUCacheDesign(int capacity) {
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        this.count = 0;
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n == null) {
            return -1;
        }
        update(n);
        return n.value;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if (n == null) {
            n = new Node(key, value);
            map.put(key, n);
            add(n);
            count++;
        } else {
            n.value = value;
            update(n);
        }
        if (count > capacity) {
            Node del = tail.prev;
            remove(del);
            map.remove(del.key);
            count--;
        }
    }

    // remove the current updated node and
    // add it to the head of the doubly-linked list
    private void update(Node n) {
        remove(n);
        add(n);
    }

    private void add(Node n) {
        Node after = head.next;
        head.next = n;
        n.prev = head;
        n.next = after;
        after.prev = n;
    }

    private void remove(Node n) {
        Node before = n.prev;
        Node after = n.next;
        before.next = after;
        after.prev = before;
    }
}
