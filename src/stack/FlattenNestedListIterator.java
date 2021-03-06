package stack;

/* 341. Flatten Nested List Iterator
Description:
Given a nested list of integers, implement an iterator to flatten it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,4,6].
 */

import common.NestedInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {
    public class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> st;
        public NestedIterator(List<NestedInteger> nestedList) {
            st = new Stack<>();
            for (int i = nestedList.size() - 1; i >= 0; --i) {
                st.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return st.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!st.isEmpty()) {
                NestedInteger cur = st.peek();
                if (cur.isInteger()) {
                    return true;
                }
                st.pop();
                for (int i = cur.getList().size() - 1; i >= 0; --i) {
                    st.push(cur.getList().get(i));
                }
            }
            return false;
        }
    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
}
