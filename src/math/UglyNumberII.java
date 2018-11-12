package math;

/* 264. Ugly Number II
Description:
Write a program to find the n-th ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example:
Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note:
1 is typically treated as an ugly number.
n does not exceed 1690.

Solution:
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
 */

import java.util.HashSet;
import java.util.PriorityQueue;

/* 思路
(1) 1x2, 2x2, 2x2, 3x2, 3x2, 4x2, 5x2...
(2) 1x3, 1x3, 2x3, 2x3, 2x3, 3x3, 3x3...
(3) 1x5, 1x5, 1x5, 1x5, 2x5, 2x5, 2x5...
仔细观察上述三个列表，我们可以发现每个子列表都是一个丑陋数分别乘以2,3,5，
而要求的丑陋数就是从已经生成的序列中取出来的，我们每次都从三个列表中取出当前最小的那个加入序列
 */
public class UglyNumberII {
    // Solution1: Using cache
    // Time Complexity: O(n)
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        int index2 = 0, index3 = 0, index5 = 0;
        ugly[0] = 1;
        for (int i = 1; i < n; ++i) {
            int min = Math.min(2*ugly[index2], Math.min(3*ugly[index3], 5*ugly[index5]));
            if (min == 2*ugly[index2]) index2++;
            if (min == 3*ugly[index3]) index3++;
            if (min == 5*ugly[index5]) index5++;
            ugly[i] = min;
        }
        return ugly[n-1];
    }

    // Solution2: Using min heap
    // Time Complexity: O(n log n)
    public int nthUglyNumber1(int n) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        // Use HashSet to avoid duplicates
        HashSet<Integer> set = new HashSet<Integer>();
        minHeap.offer(1);
        set.add(1);
        Integer uglyNumber = 1;
        for (int i = 1; i <=n ; ++i) {
            uglyNumber = minHeap.poll();
            if (uglyNumber <= Integer.MAX_VALUE/2 && !set.contains(uglyNumber * 2)) {
                minHeap.offer(uglyNumber * 2);
                set.add(uglyNumber * 2);
            }
            if (uglyNumber <= Integer.MAX_VALUE/3 &&!set.contains(uglyNumber * 3)) {
                minHeap.offer(uglyNumber * 3);
                set.add(uglyNumber * 3);
            }
            if (uglyNumber <= Integer.MAX_VALUE/5 &&!set.contains(uglyNumber * 5)) {
                minHeap.offer(uglyNumber * 5);
                set.add(uglyNumber * 5);
            }
        }
        return uglyNumber.intValue();
    }
}
