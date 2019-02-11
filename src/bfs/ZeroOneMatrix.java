package bfs;

/* 542. 01 Matrix
Description:
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

Example 1:
Input:
0 0 0
0 1 0
0 0 0

Output:
0 0 0
0 1 0
0 0 0

Example 2:
Input:
0 0 0
0 1 0
1 1 1

Output:
0 0 0
0 1 0
1 2 1

Note:
1. The number of elements of the given matrix will not exceed 10,000.
2. There are at least one 0 in the given matrix.
3. The cells are adjacent in only four directions: up, down, left and right.
 */

import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {
    // Solution1: BFS
    // Time Complexity: O(m * n), Space Complexity: O(m * n)
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    q.offer(new int[] {i, j}); // put all 0s in the queue.
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            for (int[] d : dir) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n || matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) {
                    continue;
                }
                q.offer(new int[] {r, c});
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
            }
        }
        return matrix;
    }

    // Solution2: Dynamic Programming
    // Time Complexity: O(m * n), Space Complexity: O(m * n)
    public int[][] updateMatrix1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int range = m * n;
        // iterate the matrix from top to bottom-left to right:
        // dp[i][j] = min(dp[i][j], min(dp[i][j−1], dp[i−1][j])+1)
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    int upCell = (i > 0) ? dp[i-1][j] : range;
                    int leftCell = (j > 0) ? dp[i][j-1] : range;
                    dp[i][j] = Math.min(upCell, leftCell) + 1;
                }
            }
        }
        // do the back iteration in the similar manner: from bottom to top-right to left
        // dp[i][j] = min(dp[i][j], min(dp[i][j+1], dp[i+1][j])+1)
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    int downCell = (i < m - 1) ? dp[i+1][j] : range;
                    int rightCell = (j < n - 1) ? dp[i][j+1] : range;
                    dp[i][j] = Math.min(dp[i][j], Math.min(downCell, rightCell) + 1);
                }
            }
        }
        return dp;
    }
}
