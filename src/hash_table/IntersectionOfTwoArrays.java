package hash_table;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 349. Intersection of Two Arrays
Description:
Given two arrays, write a function to compute their intersection.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

Note:
Each element in the result must be unique.
The result can be in any order.
 */

public class IntersectionOfTwoArrays {
    // Solution1: HashMap Solution
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int i = 0; i < nums1.length; ++i) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; ++i) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] res = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            res[i++] = num;
        }
        return res;
    }

    // Solution2: Sorting + Two Pointers
    public int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            res[k++] = num;
        }
        return res;
    }
}
