package greedy;

/* 435. Non-overlapping Intervals
Description:
Given a collection of intervals, find the minimum number of intervals
you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

Example 2:
Input: [ [1,2], [1,2], [1,2] ]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

Example 3:
Input: [ [1,2], [2,3] ]
Output: 0
Explanation: You don't need to remove any of the intervals
since they're already non-overlapping.
 */

import common.Interval;
import java.util.Arrays;

public class NonOverlappingIntervals {
    // Solution1: Greedy Solution(sorting with the end)
    // Time Complexity: O(n log n)
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0)  return 0;
        Arrays.sort(intervals, (a, b) -> a.end - b.end);
        int end = intervals[0].end;
        int count = 1;
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].start >= end) {
                end = intervals[i].end;
                count++;
            }
        }
        return intervals.length - count;
    }

    // Solution2: Greedy Solution(sorting with the start)
    // Time Complexity: O(n log n)
    public int eraseOverlapIntervals1(Interval[] intervals) {
        if (intervals.length <= 1)  return 0;
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        Interval prev = intervals[0];
        int count = 0;
        for (int i = 1; i < intervals.length; ++i) {
            Interval cur = intervals[i];
            if (cur.start < prev.end) {
                count++;
                // find the minimum number of interval we need to remove
                // we choose to remove the interval which has the larger end
                if (cur.end < prev.end) {
                    prev = cur;
                }
            } else {
                prev = cur;
            }
        }
        return count;
    }
}
