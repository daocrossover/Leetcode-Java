package math;

/* 441. Arranging Coins
Description:
You have a total of n coins that you want to form in a staircase shape,
where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:
n = 5
The coins can form the following rows:
¤
¤ ¤
¤ ¤
Because the 3rd row is incomplete, we return 2.

Example 2:
n = 8
The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤
Because the 4th row is incomplete, we return 3.
 */

public class ArrangingCoins {
    // Solution1: Math Solution
    // sum = (x + 1) * x / 2, x = (-1 + sqrt(8 * n + 1)) / 2
    public int arrangeCoins(int n) {
        return (int)((-1 + Math.sqrt(1 + 8 * (long)n)) / 2);
    }

    // Solution2: Iterative Solution
    public int arrangeCoins1(int n) {
        int k = 0;
        while (n >= k + 1) {
            n -= (k + 1);
            k++;
        }
        return k;
    }

    // Solution3: Binary Search
    public int arrangeCoins2(int n) {
        if (n <= 1) return n;
        long low = 1, high = n;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (mid * (mid + 1) / 2 <= n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return (int)low - 1;
    }
}
