package array;

/* 228. Summary Ranges
Description:
Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:
Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.

Example 2:
Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums.length) {
            while (i < nums.length - 1 && nums[i] + 1 == nums[i+1]) {
                i++;
            }
            String tmp;
            if (i != j) {
                tmp = String.valueOf(nums[j]) + "->" + String.valueOf(nums[i]);
            } else {
                tmp = String.valueOf(nums[j]);
            }
            res.add(tmp);
            i++;
            j = i;
        }
        return res;
    }
}
