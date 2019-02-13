package recursion;

/* 427. Construct Quad Tree
Description:
We want to use quad trees to store an N x N boolean grid.
Each cell in the grid can only be true or false.
The root node represents the whole grid.
For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same.

Each node has another two boolean attributes : isLeaf and val.
isLeaf is true if and only if the node is a leaf node.
The val attribute for a leaf node contains the value of the region it represents.

Your task is to use a quad tree to represent a given grid.
The following example may help you understand the problem better:

Given the 8 x 8 grid below, we want to construct the corresponding quad tree:

Note:
N is less than 1000 and guaranteened to be a power of 2.
If you want to know more about the quad tree, you can refer to its wiki.
 */


import common.Node;

public class ConstructQuadTree {
    public Node construct(int[][] grid) {
        return build(0, grid.length - 1, 0, grid.length - 1, grid);
    }

    private Node build(int r1, int r2, int c1, int c2, int[][] grid) {
        if (r1 > r2 || c1 > c2) {
            return null;
        }
        int val = grid[r1][c1];
        boolean isLeaf = true;
        for (int i = r1; i <= r2; ++i) {
            for (int j = c1; j <= c2; ++j) {
                if (grid[i][j] != val) {
                    isLeaf = false;
                    break;
                }
            }
        }
        if (isLeaf) {
            return new Node(val == 1, true, null, null, null, null);
        }
        int rowMid = (r1 + r2) / 2, colMid = (c1 + c2) / 2;
        return new Node(false, false,
                build(r1, rowMid, c1, colMid, grid),
                build(r1, rowMid, colMid+1, c2, grid),
                build(rowMid+1, r2, c1, colMid, grid),
                build(rowMid+1, r2, colMid+1, c2, grid));
    }
}
