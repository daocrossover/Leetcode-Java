package two_pointers;

/* 11. Container With Most Water
Description:
Given n non-negative integers a1, a2, ..., an ,
where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container,
such that the container contains the most water.

Example:
Input: [1,8,6,2,5,4,8,3,7]
Output: 49

Note: You may not slant the container and n is at least 2.
 */

public class ContainerWithMostWater {
    // Solution: Two Pointers
    public int maxArea(int[] height) {
        int max = 0;
        int start = 0, end = height.length - 1;
        while (start < end) {
            max = Math.max(max, Math.min(height[start], height[end]) * (end - start));
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return max;
    }
}
