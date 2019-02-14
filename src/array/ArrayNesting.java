package array;

/* 565. Array Nesting
Description:
A zero-indexed array A of length N contains all integers from 0 to N-1.
Find and return the longest length of set S, where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }
subjected to the rule below.

Suppose the first element in S starts with the selection of element A[i] of index = i,
the next element in S should be A[A[i]], and then A[A[A[i]]]…
By that analogy, we stop adding right before a duplicate element occurs in S.

Example 1:
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation:
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}

Note:
N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of A is an integer within the range [0, N-1].
 */

public class ArrayNesting {
    // Solution1: without extra space but modify the nums
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            int pos = i, len = 0;
            for (int j = pos; nums[j] >= 0; j = pos) {
                pos = nums[j];
                nums[j] = -1;
                len++;
            }
            res = Math.max(res, len);
        }
        return res;
    }

    // Solution2: with extra space but not modify the nums
    public int arrayNesting1(int[] nums) {
        int res = 0;
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            int len = 0;
            while (!visited[i]) {
                visited[i] = true;
                len++;
                i = nums[i];
            }
            res = Math.max(res, len);
        }
        return res;
    }
}
