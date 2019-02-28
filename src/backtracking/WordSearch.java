package backtracking;

/* 79. Word Search
Description:
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (backtrack(board, visited, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, boolean[][] visited, int i, int j, int index, String word) {
        // match in the end
        if (index == word.length()) {
            return true;
        }
        // (i, j) over boundary or (i, j) has been visited or doesn't match
        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 ||
                board[i][j] != word.charAt(index) || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        if (backtrack(board, visited, i-1, j, index+1, word) ||
                backtrack(board, visited, i+1, j, index+1, word) ||
                backtrack(board, visited, i, j-1, index+1, word) ||
                backtrack(board, visited, i, j+1, index+1, word)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }
}
