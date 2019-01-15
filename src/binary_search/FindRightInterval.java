package binary_search;

/* 436. Find Right Interval
Description:
Given a set of intervals, for each of the interval i,
check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i,
which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index,
which means that the interval j has the minimum start point to build the "right" relationship for interval i.
If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.

Example 1:
Input: [ [1,2] ]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.

Example 2:
Input: [ [3,4], [2,3], [1,2] ]
Output: [-1, 0, 1]
Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.

Example 3:
Input: [ [1,4], [2,3], [3,4] ]
Output: [-1, 2, -1]
Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.
 */

import common.Interval;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FindRightInterval {
    // Solution1: Binary Search + Sorting + HashMap
    public int[] findRightInterval(Interval[] intervals) {
        Interval[] clone = intervals.clone();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < intervals.length; ++i) {
            map.put(intervals[i].start, i);
        }
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        int[] res = new int[clone.length];
        for (int i = 0; i < clone.length; ++i) {
            int start = BinarySearch(intervals, clone[i].end);
            if (start >= clone[i].end) {
                res[i] = map.get(start);
            } else {
                res[i] = -1;
            }
        }
        return res;
    }

    private int BinarySearch(Interval[] intervals, int target) {
        int low = 0, high = intervals.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (intervals[mid].start < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return intervals[low].start;
    }

    // Solution2: TreeMap
    public int[] findRightInterval1(Interval[] intervals) {
        int len = intervals.length;
        int[] res = new int[len];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < len; ++i) {
            map.put(intervals[i].start, i);
        }
        for (int i = 0; i < len; ++i) {
            Integer ceilVal = map.ceilingKey(intervals[i].end);
            res[i] = (ceilVal==null) ? -1 : map.get(ceilVal);
        }
        return res;
    }
}
