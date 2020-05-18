package two_pointers;

/* 202. Happy Number
Description:
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits,
and repeat the process until the number equals 1 (where it will stay),
or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy numbers.

Example:
Input: 19
Output: true
Explanation:
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

Solution:
Using HashSet or fast/slow pointers to judge the loop cycle
 */

import java.util.HashSet;

public class HappyNumber {
    // Solution1: Hashset
    public boolean isHappy(int n) {
        if (n < 1) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            int m = 0;
            while (n != 0) {
                int tmp = n % 10;
                m += tmp * tmp;
                n /= 10;
            }
            n = m;
        }
        return n == 1;
    }

    // Solution2: Fast and slow pointers
    public boolean isHappy1(int n) {
        if (n < 1) {
            return false;
        }
        int slow = n, fast = n;
        while (fast != 1 && nextNumber(fast) != 1) {
            slow = nextNumber(slow);
            fast = nextNumber(nextNumber(fast));
            if (slow == fast) break;
        }
        return fast == 1 || nextNumber(fast) == 1;
    }

    int nextNumber(int n) {
        int sum = 0;
        int digit = 0;
        while (n > 0) {
            digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
