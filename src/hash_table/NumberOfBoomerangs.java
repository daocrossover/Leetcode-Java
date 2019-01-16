package hash_table;

/* 447. Number of Boomerangs
Description:
Given n points in the plane that are all pairwise distinct,
a "boomerang" is a tuple of points (i, j, k) such that
the distance between i and j equals the distance between i and k
(the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and
coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; ++i) {
            for (int j = 0; j < points.length; ++j) {
                if (i == j) {
                    continue;
                }
                int dis = distance(points[i], points[j]);
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }
            for (int val : map.values()) {
                res += val * (val - 1);
            }
            map.clear();
        }
        return res;
    }

    private int distance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }
}
