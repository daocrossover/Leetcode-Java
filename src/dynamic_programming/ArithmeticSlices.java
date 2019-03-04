package dynamic_programming;

/* 413. Arithmetic Slices
Description:
A sequence of number is called arithmetic if it consists of at least three elements
and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9

The following sequence is not arithmetic.
1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given.
A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.

Example:
A = [1, 2, 3, 4]
return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */

public class ArithmeticSlices {
    // Solution1: Dynamic Programming
    // dp[i]: the number of arithmetic slices before index i
    // dp[i] = dp[i-1] + 1;
    // Time Complexity: O(n), Space Complexity: O(n)
    public int numberOfArithmeticSlices(int[] A) {
        int[] dp = new int[A.length];
        int sum = 0;
        for (int i = 2; i < A.length; ++i) {
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
                dp[i] = dp[i-1] + 1;
                sum += dp[i];
            }
        }
        return sum;
    }

    // Solution2: Constant Space Dynamic Programming
    // observe that we only require the element dp[i-1] to determine the value to be entered at dp[i].
    // Thus, instead of making use of a 1-D array to store the required data,
    // we can simply keep a track of just the last element.
    public int numberOfArithmeticSlices1(int[] A) {
        int dp = 0;
        int sum = 0;
        for (int i = 2; i < A.length; ++i) {
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
                dp = dp + 1;
                sum += dp;
            } else {
                dp = 0;
            }
        }
        return sum;
    }
}
