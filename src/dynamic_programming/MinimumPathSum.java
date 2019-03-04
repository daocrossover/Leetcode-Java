package dynamic_programming;

/* 64. Minimum Path Sum
Description:
Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */

public class MinimumPathSum {
    // Solution1: 2-D Dynamic Programming
    // dp[i][j]: minimum path sum of arriving at point (i, j)
    // dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // initiate the first column
        for (int i = 1; i < m; ++i) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        // initiate the first row
        for (int j = 1; j < n; ++j) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m-1][n-1];
    }

    // Solution2: 1-D Dynamic Programming
    // As can be seen, each time when we update dp[i][j], we only need dp[i-1][j] (at the current column)
    // and dp[i][j-1] (at the left column). So we need not maintain the full m*n matrix.
    // Maintaining a column is enough and now we have the following code.
    public int minPathSum1(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        for (int i = 1; i < m; ++i) {
            dp[i] = dp[i-1] + grid[i][0];
        }
        for (int j = 1; j < n; ++j) {
            dp[0] += grid[0][j];
            for (int i = 1; i < m; ++i) {
                dp[i] = grid[i][j] + Math.min(dp[i-1], dp[i]);
            }
        }
        return dp[m-1];
    }
}
