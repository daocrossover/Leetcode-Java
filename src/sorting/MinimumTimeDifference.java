package sorting;

/* 539. Minimum Time Difference
Description:
Given a list of 24-hour clock time points in "Hour:Minutes" format,
find the minimum minutes difference between any two time points in the list.

Example 1:
Input: ["23:59","00:00"]
Output: 1

Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumTimeDifference {
    // Solution1: Sorting
    // Time Complexity: O(n log n), Space Complexity: O(n)
    public int findMinDifference(List<String> timePoints) {
        List<Integer> times = new ArrayList<>();
        for (String tp : timePoints) {
            int h = Integer.parseInt(tp.substring(0, 2));
            int m = Integer.parseInt(tp.substring(3, 5));
            times.add(60 * h + m);
        }
        Collections.sort(times);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < times.size(); ++i) {
            min = Math.min(min, times.get(i) - times.get(i-1));
        }
        // corner case
        min = Math.min(min, times.get(0) + 1440 - times.get(times.size() - 1));
        return min;
    }

    // Solution2: Bucket
    // Time Complexity: O(n), Space Complexity: O(1)
    public int findMinDifference1(List<String> timePoints) {
        boolean[] position = new boolean[24 * 60];
        for (String tp : timePoints) {
            int h = Integer.parseInt(tp.substring(0, 2));
            int m = Integer.parseInt(tp.substring(3, 5));
            if (position[60 * h + m]) {
                return 0;
            }
            position[60 * h + m] = true;
        }
        int prev = 0, min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for (int i = 0; i < 24 * 60; ++i) {
            if (position[i]) {
                if (first != Integer.MAX_VALUE) {
                    min = Math.min(min, i - prev);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }
        return Math.min(min, first + 24 * 60 - last);
    }
}
