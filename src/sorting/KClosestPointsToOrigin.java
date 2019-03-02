package sorting;

/* 973. K Closest Points to Origin
Description:
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
(Here, the distance between two points on a plane is the Euclidean distance.)
You may return the answer in any order.
The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)

Note:
1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
 */

import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
    // Solution1: Using Max Heap
    // Time Complexity: O(NlogK), Space Complexity: O(K)
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] * b[0] + b[1] * b[1] - (a[0] * a[0] + a[1] * a[1]));
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][2];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.poll();
        }
        return res;
    }

    // Solution2: Quick Select
    // the idea is the same as the quick sort
    // Time Complexity: O(n) (average), O(n^2) (the worst)
    public int[][] kClosest1(int[][] points, int K) {
        int low = 0, high = points.length - 1;
        while (low <= high) {
            int mid = helper(points, low, high);
            if (mid == K) {
                break;
            } else if (mid < K) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = points[K];
        }
        return res;
    }

    private int helper(int[][] points, int l, int r) {
        int[] pivot = points[l];
        while (l < r) {
            while (l < r && compare(points[r], pivot) >= 0) {
                r--;
            }
            points[l] = points[r];
            while (l < r && compare(points[l], pivot) <= 0) {
                l++;
            }
            points[r] = points[l];
        }
        points[l] = pivot;
        return l;
    }

    private int compare(int[] a, int[] b) {
        return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1];
    }
}
