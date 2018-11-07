/* 165. Compare Version Numbers
Description:
Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the '.' character.
The '.' character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three",
it is the fifth second-level revision of the second first-level revision.

Example 1:
Input: version1 = "0.1", version2 = "1.1"
Output: -1

Example 2:
Input: version1 = "1.0.1", version2 = "1"
Output: 1

Example 3:
Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1
 */

public class CompareVersionNumbers {
    // Without split()
    public int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
        int len1 = version1.length(), len2 = version2.length();
        while (i < len1 || j < len2) {
            int tmp1 = 0, tmp2 = 0;
            while (i < len1 && version1.charAt(i) != '.') {
                tmp1 = tmp1 * 10 + version1.charAt(i) - '0';
                i++;
            }

            while (j < len2 && version2.charAt(j) != '.') {
                tmp2 = tmp2 * 10 + version2.charAt(j) - '0';
                j++;
            }

            if (tmp1 < tmp2) {
                return -1;
            } else if (tmp1 > tmp2) {
                return 1;
            } else {
                i++;
                j++;
            }
        }
        return 0;
    }

    // Using split()
    public int compareVersionWithSplit(String version1, String version2) {
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");
        int len = Math.max(parts1.length, parts2.length);
        for (int i = 0; i < len; ++i) {
            Integer num1 = (i < parts1.length ? Integer.parseInt(parts1[i]) : 0);
            Integer num2 = (i < parts2.length ? Integer.parseInt(parts2[i]) : 0);
            int compare = num1.compareTo(num2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
}
