package binary_search;

/* 378. Kth Smallest Element in a Sorted Matrix
Description:
Given a n x n matrix where each of the rows and columns are sorted in ascending order,
find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,
return 13.

Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
 */

import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {
    // Solution1: Binary Search
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        int low = matrix[0][0], high = matrix[matrix.length-1][matrix.length-1];
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = 0, j = matrix[0].length - 1;
            for (int i = 0; i < matrix.length; ++i) {
                while (j >= 0 && matrix[i][j] > mid) {
                    j--;
                }
                count += (j + 1);
            }
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // Solution2: Binary Search II
    // count starting from the left-bottom of the matrix
    // Time Complexity: O(n log(max - min))
    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = getLessEqual(matrix, mid);
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private int getLessEqual(int[][] matrix, int val) {
        int res = 0;
        int n = matrix.length, i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > val) {
                i--;
            } else {
                res += i + 1;
                j++;
            }
        }
        return res;
    }

    // Solution3: Min Heap
    // Time Complexity: O(k log n)
    public int kthSmallest2(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int j = 0; j < matrix[0].length; ++j) {
            minHeap.offer(new int[]{0, j, matrix[0][j]});
        }
        for (int i = 0; i < k - 1; ++i) {
            int[] top = minHeap.poll();
            if (top[0] == matrix.length - 1) {
                continue;
            }
            minHeap.offer(new int[]{top[0] + 1, top[1], matrix[top[0] + 1][top[1]]});
        }
        return minHeap.poll()[2];
    }
}
