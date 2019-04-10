package dfs;

/* 329. Longest Increasing Path in a Matrix
Description:
Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
Input: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

public class LongestIncreasingPathInAMatrix {
    // DFS + Memoization
    private int[][] dir = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length ==0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] state = new int[m][n];
        int maxLen = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int len = dfs(matrix, state, i, j);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    private int dfs(int[][] matrix, int[][] state, int i, int j) {
        if (state[i][j] != 0) {
            return state[i][j];
        }
        int maxLen = 1;
        for (int d = 0; d < dir.length; ++d) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int len = 1 + dfs(matrix, state, x, y);
            maxLen = Math.max(len, maxLen);
        }
        state[i][j] = maxLen;
        return maxLen;
    }
}
