package array;

/* 84. Largest Rectangle in Histogram
Description:
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:
Input: [2,1,5,6,2,3]
Output: 10
 */

/* Solution1: Dynamic Programming
1. For any position i the maximum rectangle is of width r - l - 1
where r is the first position of the bar to the right with height h[r] < h[i]
and l is the first position of the bar to the left which height h[l] < h[i]
so maxArea = Math.max(maxArea, heights[i] * (r - l - 1))

2. To calculate lessFromRight and lessFromLeft arrays, the trivial solution is O(n^2)
for (int i = 1; i < height.length; i++) {
    int p = i - 1;
    while (p >= 0 && height[p] >= height[i]) {
        p--;
    }
    lessFromLeft[i] = p;
}

but we don't need to rescan each item to the left,
we can reuse results of previous calculations and "jump" through indices in quick manner
for (int i = 1; i < heights.length; ++i) {
    int p = i - 1;
    while (p >= 0 && heights[p] >= heights[i]) {
        p = lessFromLeft[p];
    }
    lessFromLeft[i] = p;
}
the time complexity can be simplified to O(n)
 */

/* Solution2: Monotonic Stack
monotonically increasing stack
could find the first element from the left that is smaller than the current number
example: [1, 3, 4, 2]
height   1   3    4   2
index    0   1    2   3
1.          peek  tp  i   area = 4 * (3 - 1 - 1) = 4
height   1   3    2
index    0   1    3
2.     peek  tp   i       area = 3 * (3 - 0 - 1) = 6
height   1   2    0
index    0   3    4
3.     peek  tp   i       area = 2 * (4 - 0 - 1) = 6
height   1   0
index    0   4
4.      tp   i            stack is empty, area = 1 * 4 = 4
Time Complexity: O(n), Space Complexity: O(n)
 */
import java.util.Stack;

public class LargestRectangleInHistogram {
    // Solution1: Dynamic Programming
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[heights.length];
        int[] lessFromRight = new int[heights.length];
        lessFromLeft[0] = -1;
        lessFromRight[heights.length - 1] = heights.length;
        // find the first position to the left with h[l] < h[i] for each i
        for (int i = 1; i < heights.length; ++i) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }
        // find the first position to the right with h[l] < h[i] for each i
        for (int i = heights.length - 2; i >= 0; --i) {
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }
        int maxArea = 0;
        for (int i = 0; i < heights.length; ++i) {
            maxArea = Math.max(maxArea, (lessFromRight[i] - lessFromLeft[i] - 1) * heights[i]);
        }
        return maxArea;
    }

    // Solution2: Using Stack
    public int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; ++i) {
            // finally push height 0 to the stack to ensure all heights have been tackled
            int h = (i == len ? 0 : heights[i]);
            if (s.isEmpty() || h >= heights[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }
}
