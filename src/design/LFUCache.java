package design;

/* 460. LFU Cache
Description:
Design and implement a data structure for Least Frequently Used (LFU) cache.
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.
When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item
For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:
LFUCache cache = new LFUCache(2);
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
 */

import java.util.HashMap;

public class LFUCache {
    // doubly-linked list data structure
    // sorted by frequency (second level sorting is most recent).
    private class Node {
        int key, value;
        int frequency;
        Node prev, next;
        Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.frequency = freq;
        }
        Node() {
            this(0, 0, -1);
        }
    }

    private HashMap<Integer, Node> keyMap; // key -> node reference
    private HashMap<Integer, Node> freqMap; // frequency -> head node of that frequency
    private Node head, tail;
    private int capacity, size;

    public LFUCache(int capacity) {
        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key) {
        if (capacity <= 0 || !keyMap.containsKey(key)) {
            return -1;
        }
        Node node = keyMap.get(key);
        delete(node);
        insert(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            node.value = value;
            get(key);
        } else {
            if (size == capacity) {
                delete(tail.prev);
                size--;
            }
            Node node = new Node(key, value, 0);
            insert(node);
            size++;
        }
    }

    private void delete(Node node) {
        keyMap.remove(node.key);
        Node prev = node.prev;
        Node next = node.next;
        if (prev.frequency != node.frequency) {
            // the node is the head of its frequency list part
            if (next.frequency == node.frequency) {
                // the frequency list part has more than one node
                freqMap.put(node.frequency, next);
            } else {
                // the frequency list part only has the node to be deleted
                freqMap.remove(node.frequency);
            }
        }
        prev.next = next;
        next.prev = prev;
    }

    private void insert(Node node) {
        int newFreq = node.frequency + 1;
        Node newFreqHead = freqMap.get(newFreq);
        Node curFreqHead = freqMap.get(node.frequency);
        if (newFreqHead == null) {
            if (curFreqHead == null) {
                Node next;
                if (node.frequency == 0) {
                    // the node is a new node
                    next = tail;
                } else {
                    // the node is a new frequency list part head
                    next = node.next;
                }
                prepend(next, node);
            } else {
                prepend(curFreqHead, node);
            }
        } else {
            prepend(newFreqHead, node);
        }
        node.frequency = newFreq;
        freqMap.put(newFreq, node);
        keyMap.put(node.key, node);
    }

    private void prepend(Node after, Node node) {
        Node prev = after.prev;
        prev.next = node;
        node.prev = prev;
        node.next = after;
        after.prev = node;
    }
}
