package backtracking;

/* 526. Beautiful Arrangement
Description:
Suppose you have N integers from 1 to N.
We define a beautiful arrangement as an array that is constructed
by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.

Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2
Explanation:
The first beautiful arrangement is [1, 2]:
Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:
Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.

Note:
N is a positive integer and will not exceed 15.
 */

public class BeautifulArrangement {
    private int res = 0;
    public int countArrangement(int N) {
        if (N == 0) {
            return 0;
        }
        backtracking(N, 1, new boolean[N + 1]);
        return res;
    }

    private void backtracking(int N, int pos, boolean[] used) {
        if (pos > N) {
            res++;
            return;
        }

        for (int i = 1; i <= N; ++i) {
            if (!used[i] && (i % pos == 0 || pos % i == 0)) {
                used[i] = true;
                backtracking(N, pos + 1, used);
                used[i] = false;
            }
        }
    }
}
