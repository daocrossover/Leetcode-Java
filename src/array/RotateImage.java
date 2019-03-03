package array;

/* 48. Rotate Image
Description:
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).

Note:
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
DO NOT allocate another 2D matrix and do the rotation.

Example 1:
Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]

Example 2:
Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
 */

/*
 * clockwise
 * first swap the symmetry, then reverse each row
 * 1 2 3     1 4 7     7 4 1
 * 4 5 6  => 2 5 8  => 8 5 2
 * 7 8 9     3 6 9     9 6 3
 */

/*
 * anticlockwise rotate
 * first swap the symmetry, then reverse each column
 * 1 2 3     1 4 7     3 6 9
 * 4 5 6  => 2 5 8  => 2 5 8
 * 7 8 9     3 6 9     1 4 7
 */

public class RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        // swap the symmetry
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n / 2; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
        /* anticlockwise rotate
        for (int i = 0; i < m / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[m-1-i][j];
                matrix[i][m-1-j] = tmp;
            }
        }
        */
    }
}
