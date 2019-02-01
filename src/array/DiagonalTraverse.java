package array;

/* 498. Diagonal Traverse
Description:
Given a matrix of M x N elements (M rows, N columns),
return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.
 */

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{};
        }
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m*n];
        int k = 0;
        for (int i = 0; i < m + n - 1; ++i) {
            int low = Math.max(0, i - n + 1), high = Math.min(i, m - 1);
            if (i % 2 == 0) {
                for (int j = high; j >= low; --j) {
                    res[k++] = matrix[j][i - j];
                }
            } else {
                for (int j = low; j <= high; ++j) {
                    res[k++] = matrix[j][i - j];
                }
            }
        }
        return res;
    }
}
