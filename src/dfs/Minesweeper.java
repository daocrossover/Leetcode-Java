package dfs;

/* 529. Minesweeper
Description:
Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board.
'M' represents an unrevealed mine, 'E' represents an unrevealed empty square,
'B' represents a revealed blank square that has no adjacent
(above, below, left, right, and all 4 diagonals) mines,
digit ('1' to '8') represents how many mines are adjacent to this revealed square,
and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among
all the unrevealed squares ('M' or 'E'), return the board after
revealing this position according to the following rules:

1. If a mine ('M') is revealed, then the game is over - change it to 'X'.
2. If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B')
and all of its adjacent unrevealed squares should be revealed recursively.
3. If an empty square ('E') with at least one adjacent mine is revealed,
then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.

Example 1:
Input:
[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]
Click : [3,0]

Output:
[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Example 2:
Input:
[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
Click : [1,2]

Output:
[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Note:
1. The range of the input matrix's height and width is [1,50].
2. The click position will only be an unrevealed square ('M' or 'E'),
which also means the input board contains at least one clickable square.
3. The input board won't be a stage when game is over (some mines have been revealed).
4. For simplicity, not mentioned rules should be ignored in this problem. For example,
you don't need to reveal all the unrevealed mines when the game is over,
consider any cases that you will win the game or flag any squares.
 */

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
    // Solution1: DFS
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            // if click on a mine ('M'), mark it as 'X', stop further search.
            board[x][y] = 'X';
        } else {
            // If click on an empty cell ('E')
            // Get number of mines first.
            int count = 0;
            for (int row = -1; row < 2; ++row) {
                for (int col = -1; col < 2; ++col) {
                    if (row == 0 && col == 0) {
                        continue;
                    }
                    int r = x + row, c = y + col;
                    if (r < 0 || r >= m || c < 0 || c >= n) {
                        continue;
                    }
                    if (board[r][c] == 'M' || board[r][c] == 'X') {
                        count++;
                    }
                }
            }
            if (count > 0) {
                // If it is not a 'B', stop further DFS.
                board[x][y] = (char) ('0' + count);
            } else {
                // Continue DFS to adjacent cells.
                board[x][y] = 'B';
                for (int row = -1; row < 2; ++row) {
                    for (int col = -1; col < 2; ++col) {
                        if (row == 0 && col == 0) {
                            continue;
                        }
                        int r = x + row, c = y + col;
                        if (r < 0 || r >= m || c < 0 || c >= n) {
                            continue;
                        }
                        if (board[r][c] == 'E') {
                            updateBoard(board, new int[] {r, c});
                        }
                    }
                }
            }
        }
        return board;
    }

    // Solution2: BFS
    public char[][] updateBoard1(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(click);
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int x = cell[0], y = cell[1];
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
            } else {
                int count = 0;
                for (int row = -1; row < 2; ++row) {
                    for (int col = -1; col < 2; ++col) {
                        if (row == 0 && col == 0) {
                            continue;
                        }
                        int r = x + row, c = y + col;
                        if (r < 0 || r >= m || c < 0 || c >= n) {
                            continue;
                        }
                        if (board[r][c] == 'M' || board[r][c] == 'X') {
                            count++;
                        }
                    }
                }
                if (count > 0) {
                    board[x][y] = (char) ('0' + count);
                } else {
                    board[x][y] = 'B';
                    for (int row = -1; row < 2; ++row) {
                        for (int col = -1; col < 2; ++col) {
                            if (row == 0 && col == 0) {
                                continue;
                            }
                            int r = x + row, c = y + col;
                            if (r < 0 || r >= m || c < 0 || c >= n) {
                                continue;
                            }
                            if (board[r][c] == 'E') {
                                q.offer(new int[] {r, c});
                                board[r][c] = 'B'; // avoid to be added again.
                            }
                        }
                    }
                }
            }
        }
        return board;
    }
}
