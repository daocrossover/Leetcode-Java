package dynamic_programming;

/* 552. Student Attendance Record II
Description:
Given a positive integer n, return the number of all possible attendance records with length n,
which will be regarded as rewardable. The answer may be very large, return it after mod 10^9 + 7.

A student attendance record is a string that only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.

A record is regarded as rewardable if it doesn't contain more than
one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times.

Note: The value of n won't exceed 100,000.
 */

/* Solution:
1. Divide the whole problem into sub-problems
Total = ended with P + ended with L + (insert A into Strings ended with P or L)
P(n) is the total number of all possible attendance records ended with 'P' with length n without A.
PorL(n) is the total number of all possible attendance records ended with 'L' or 'P' with length n without A.

2. Calculate P(n)
If we add a 'P' to an attendance records with length n - 1,
we will get an attendance records ended with 'P' with length n.
if the (n-1)th character is 'P' or 'L', we can add 'P', so
P(n) = PorL(n-1) (n >= 2)

3. Calculate PorL(n)
If we add a 'P' or 'L' to an attendance records with length n - 1,
we will get an attendance records ended with 'P' or 'L' with length n.
If we add 'P', the PorL(n) = P(n)
If we add 'L':
(1) If its (n-1)th character is 'P', we can add 'L'.("PL")
(2) If its (n-1)th character is 'L':
    (a) If its (n-2)th character is 'P', we can add 'L'.("PLL")
    (b) If its (n-2)th character is 'L', we can NOT add 'L'.("LLL")
Therefore, PorL(n) = P(n) + P(n-1) + P(n-2) (n >= 3)

4. Insert 'A' into strings with length n - 1, and add the res
 */
public class StudentAttendanceRecordII {
    private static final int M = 1000000007;
    public int checkRecord(int n) {
        long[] PorL = new long[n + 1]; // ending with P or L, no A
        long[] P = new long[n + 1]; // ending with P, no A
        PorL[0] = P[0] = 1;
        P[1] = 1;
        PorL[1] = 2;
        for (int i = 2; i <= n; ++i) {
            P[i] = PorL[i - 1];
            PorL[i] = (P[i] + P[i - 1] + P[i - 2]) % M;
        }
        long res = PorL[n];
        // inserting A into (n-1)-length strings
        for (int i = 0; i < n; ++i) {
            long a = (PorL[i] * PorL[n - 1 - i]) % M;
            res = (res + a) % M;
        }
        return (int) res;
    }
}
