package two_pointers;

/* 905. Sort Array By Parity
Description:
Given an array A of non-negative integers, return an array consisting of all the even elements of A,
followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

Example 1:
Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

Note:
1 <= A.length <= 5000
0 <= A[i] <= 5000
 */

public class SortArrayByParity {
    // Solution1: Using Two Pointers
    public int[] sortArrayByParity(int[] A) {
        int start = 0, end = A.length - 1;
        while (start < end) {
            while (start < end && (A[start] & 1) == 0) {
                start++;
            }
            while (start < end && (A[end] & 1) == 1) {
                end--;
            }
            int tmp = A[start];
            A[start] = A[end];
            A[end] = tmp;
            start++;
            end--;
        }
        return A;
    }

    // Solution2: Using Two Pointers
    // if we change the parity condition, we can just change the code in the func
    public int[] sortArrayByParity1(int[] A) {
        int start = 0, end = A.length - 1;
        while (start < end) {
            while (start < end && func(A[start])) {
                start++;
            }
            while (start < end && !func(A[end])) {
                end--;
            }
            int tmp = A[start];
            A[start] = A[end];
            A[end] = tmp;
            start++;
            end--;
        }
        return A;
    }

    private boolean func(int n) {
        return (n & 1) == 0;
    }
}
