package sorting;

/* 179. Largest Number
Description:
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:
Input: [10,2]
Output: "210"

Example 2:
Input: [3,30,34,5,9]
Output: "9534330"

Note: The result may be very large, so you need to return a string instead of an integer.
 */

/* 思路
写一个compare函数，重新定义两个数的大小关系，
排序完后直接累加数组即可获得最大number对应的string。

compare函数：
对于两个数str1，str2，比较str1+str2与str2+str1的大小
比如123，321，要比较123321与321123的大小。
按照从大到小排列
 */

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        // Convert int array to String array, so we can sort later on
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            numStrings[i] = String.valueOf(nums[i]);
        }
        // Comparator to decide which string should come first in concatenation
        Comparator<String> comp = new Comparator<>() {
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1); // reverse order here, so we can do append() later
            }
        };

        Arrays.sort(numStrings, comp);
        // corner case:
        if (numStrings[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s: numStrings) {
            sb.append(s);
        }
        return sb.toString();
    }
}
