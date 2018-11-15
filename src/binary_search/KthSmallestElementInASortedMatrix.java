package binary_search;

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

    // Solution2: Min Heap
    // Time Complexity: O(k log n)
    public int kthSmallest1(int[][] matrix, int k) {
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
