import java.util.Stack;

// Using one stack:
public class MinStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();
    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        // only push the old minimum value when the current
        // minimum value changes after pushing the new value x
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value,
        // pop twice and change the current minimum value to the last minimum value.
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

// Using two stacks:
class MinStack1 {
    Stack<Integer> min = new Stack<>();
    Stack<Integer> stack = new Stack<>();
    /** initialize your data structure here. */
    public MinStack1() {

    }

    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || min.peek() >= x) {
            min.push(x);
        }
    }

    public void pop() {
        if (stack.pop().equals(min.peek())) {
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
