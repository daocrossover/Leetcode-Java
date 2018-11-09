package stack;

/* 225. Implement Stack using Queues
Description:
Implement the following operations of a stack using queues.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.

Example:
MyStack stack = new MyStack();
stack.push(1);
stack.push(2);
stack.top();   // returns 2
stack.pop();   // returns 2
stack.empty(); // returns false

Notes:
1. You must use only standard operations of a queue
-- which means only push to back, peek/pop from front, size, and is empty operations are valid.
2. Depending on your language, queue may not be supported natively.
You may simulate a queue by using a list or deque (double-ended queue),
as long as you use only standard operations of a queue.
3. You may assume that all operations are valid
(for example, no pop or top operations will be called on an empty stack).
 */

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {
    // One Queue Solution:
    // push(): O(n)
    // pop(): O(1)
    // top(): O(1)
    class MyStack {
        Queue<Integer> q;

        /** Initialize your data structure here. */
        public MyStack() {
            this.q = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            q.add(x);
            for (int i = 0; i < q.size() - 1; ++i) {
                q.add(q.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return q.poll();
        }

        /** Get the top element. */
        public int top() {
            return q.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q.isEmpty();
        }
    }

    // Two Queues' Solution1:
    // When push elements, choose a queue which is not empty(whichever when both are empty).
    // When pop() or top(), first pop all elements of the queue except the tail into another empty queue,
    // and then pop the tail which is your want.
    // push(): O(1)
    // pop(): O(n)
    // top(): O(n)
    class MyStack1{
        // using two queue. The pop and top are inefficient.
        private Queue<Integer> q1 = new LinkedList<Integer>();
        private Queue<Integer> q2 = new LinkedList<Integer>();

        public void push(int x) {
            if (!q1.isEmpty()) {
                q1.add(x);
            } else {
                q2.add(x);
            }
        }

        public void pop() {
            if (q1.isEmpty()) {
                int size = q2.size();
                for (int i = 1; i < size; ++i) {
                    q1.add(q2.poll());
                }
                q2.poll();
            } else {
                int size = q1.size();
                for (int i = 1; i < size; ++i) {
                    q2.add(q1.poll());
                }
                q1.poll();
            }
        }

        public int top() {
            int res;
            if (q1.isEmpty()) {
                int size = q2.size();
                for (int i = 1; i < size; ++i) {
                    q1.add(q2.poll());
                }
                res = q2.poll();
                q1.add(res);
            } else {
                int size = q1.size();
                for (int i = 1; i < size; ++i) {
                    q2.add(q1.poll());
                }
                res = q1.poll();
                q2.add(res);
            }
            return res;
        }

        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }

    // Two Queues' Solution2:
    // when push an element, choose one empty queue(whichever when both are empty) to add this element,
    // and then push all elements of the other queue into the chosen queue.
    // After that, the newest element is at the head of the chosen queue so that
    // whenever you want to do pop() or top(), you always get the newest element.
    // push(): O(n)
    // pop(): O(1)
    // top(): O(1)
    class MyStack2 {
        // using two queue. The push is inefficient.
        private Queue<Integer> q1 = new LinkedList<Integer>();
        private Queue<Integer> q2 = new LinkedList<Integer>();

        public void push(int x) {
            if (q1.isEmpty()) {
                q1.add(x);
                for(int i = 0; i < q2.size(); ++i) {
                    q1.add(q2.poll());
                }
            } else {
                q2.add(x);
                for(int i = 0; i < q1.size(); ++i) {
                    q2.add(q1.poll());
                }
            }
        }

        public void pop() {
            if (!q1.isEmpty()) {
                q1.poll();
            } else {
                q2.poll();
            }
        }

        public int top() {
            return q1.isEmpty() ? q2.peek() : q1.peek();
        }

        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }
}
