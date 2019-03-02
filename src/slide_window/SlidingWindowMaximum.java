package slide_window;

/* 239. Sliding Window Maximum
Description:
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position.
Return the max sliding window.

Example:
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7]

Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Note:
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    // Solution1: Deque
    // Time Complexity: O(n), Space Complexity: O(k)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int ri = 0;
        for (int i = 0; i < n; ++i) {
            // remove numbers out of range k from the front
            if (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // Remove smaller numbers in k range as they are useless from the end
            // Now only those elements within [i-(k-1),i] are in the deque.
            // We then discard elements smaller than nums[i] from the tail.
            // This is because if nums[x]<nums[i] and x<i, then nums[x] has no chance to be the "max" in [i-(k-1),i],
            // or any other subsequent window: a[i] would always be a better candidate.
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            // q contains index... res contains content
            q.offer(i);
            if (i >= k - 1) {
                res[ri++] = nums[q.peek()];
            }
        }
        return res;
    }
}
