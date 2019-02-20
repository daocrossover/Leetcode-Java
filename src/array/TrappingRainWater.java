package array;

/* 42. Trapping Rain Water
Description:
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */

import java.util.Stack;

public class TrappingRainWater {
    // Solution1: Dynamic Programming
    // Time Complexity: O(n), Space Complexity: O(n)
    // Three pass
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0, n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        // find maximum height of bar from the left end upto an index i in the array leftMax
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        // find maximum height of bar from the right end upto an index i in the array rightMax
        rightMax[n-1] = height[n-1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        // add min(leftMax[i], rightMax[i]) - height[i] to res
        for (int i = 1; i < n - 1; ++i) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    // Solution2: Two Pointers
    // As long as rightMax[i] > leftMax[i], the water trapped depends upon the leftMax,
    // and similar is the case when leftMax[i] > rightMax[i]
    // If there is a larger bar at one end (say right),
    // we are assured that the water trapped would be dependant on height of bar in current direction (from left to right).
    // As soon as we find the bar at other end (right) is smaller,
    // we start iterating in opposite direction (from right to left).
    // Time Complexity: O(n), Space Complexity: O(1)
    // One Pass
    public int trap1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    res += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    res += rightMax - height[right];
                }
                right--;
            }
        }
        return res;
    }

    // Solution3: Monotonic Stack
    // Monotonically decreasing stack
    // could find the first element from the left that is larger than the current number.
    // Time Complexity: O(n), Space Complexity: O(n)
    // One Pass
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int res = 0, i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[stack.peek()] >= height[i]) {
                stack.push(i);
                i++;
            } else {
                // if the current height > the height of top element on the stack
                // there may be pits to contain water
                int t = stack.pop();
                if (stack.isEmpty()) continue;
                // current top of the stack: left boundary
                // i: right boundary
                // t: pit
                // width = right - left - 1
                // contained water = width * (min(height[left], height[right]) - height[pit])
                res += (Math.min(height[stack.peek()], height[i]) - height[t]) * (i - stack.peek() - 1);
            }
        }
        return res;
    }
}
