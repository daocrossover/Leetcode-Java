package sorting;

import common.Interval;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* 56. Merge Intervals
Description:
Given a collection of intervals, merge all overlapping intervals.

Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> a.start - b.start);
        LinkedList<Interval> res = new LinkedList<>();
        for (Interval i : intervals) {
            if (res.isEmpty() || i.start > res.getLast().end) {
                res.add(i);
            } else {
                res.getLast().end = Math.max(i.end, res.getLast().end);
            }
        }
        return res;
    }
}
