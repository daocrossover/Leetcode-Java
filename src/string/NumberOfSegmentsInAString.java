package string;

/* 434. Number of Segments in a string
Description:
Count the number of segments in a string,
where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:
Input: "Hello, my name is John"
Output: 5
 */

public class NumberOfSegmentsInAString {
    // Solution1: In place
    // Time Complexity: O(n), Space Complexity: O(1)
    public int countSegments(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            if ((i == 0 || s.charAt(i-1) == ' ') && s.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }

    // Solution2: Using Built-in Method
    // Time Complexity: O(n), Space Complexity: O(n)
    // Note:
    // string[] tokens = "".split("\\s++");
    // tokens.length; // 1
    // tokens[0]; // ""
    // 1. one or more leading spaces will cause split to deduce an erroneous "" token at the beginning of the string,
    // so we use the builtin trim method to remove leading and trailing spaces.
    // 2. Then, if the resulting string is the empty string, then we can simply output 0.
    public int countSegments1(String s) {
        String trimmed = s.trim();
        if (trimmed.equals("")) {
            return 0;
        }
        return trimmed.split("\\s+").length;
    }
}
