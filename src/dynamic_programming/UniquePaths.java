package dynamic_programming;

/* 62. Unique Paths
Description:
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Example 2:
Input: m = 7, n = 3
Output: 28
 */

import java.util.Arrays;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int M = m + n - 2;
        int N = Math.min(m, n) - 1;
        long res = 1;
        for (int i = 1; i <= N; ++i) {
            res *= M--;
            res /= i;
        }
        return (int)res;
    }

    // Solution2: Dynamic Programming
    // When n = 0 or m = 0 the function always returns 1 since the robot can't go left or up.
    // For all other cells. The result = uniquePaths(m-1, n) + uniquePaths(m, n-1)
    int uniquePaths1(int m, int n) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[n-1][m-1];
    }

    // save spaces, Space Complexity: O(min(m, n))
    public int uniquePaths2(int m, int n) {
        if (m > n) return uniquePaths(n, m);
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                dp[j] += dp[j-1];
            }
        }
        return dp[m-1];
    }
}
