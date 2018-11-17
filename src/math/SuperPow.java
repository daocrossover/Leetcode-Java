package math;

/* 372. Super Pow
Description:
Your task is to calculate ab mod 1337 where a is a positive integer
and b is an extremely large positive integer given in the form of an array.

Example 1:
Input: a = 2, b = [3]
Output: 8

Example 2:
Input: a = 2, b = [1,0]
Output: 1024
 */

public class SuperPow {
    // Solution1:
    // ab % k = (a%k)(b%k)%k
    // a^1234567 % k = (a^1234560 % k) * (a^7 % k) % k = (a^123456 % k)^10 % k * (a^7 % k) % k
    // Suppose f(a, b) calculates a^b % k;
    // f(a,1234567) = f(a, 1234560) * f(a, 7) % k = f(f(a, 123456),10) * f(a,7)%k;
    private static final int BASE = 1337;
    public int superPow(int a, int[] b) {
        if (a == 0 || a == 1) {
            return a;
        }
        return superPowMod(a, b, b.length-1);
    }
    // O(n), where n = b.length
    private int superPowMod(int a, int[] b, int index) {
        if (index < 0) {
            return 1;
        }
        int c = superPowMod(a, b, index-1);
        return (powMod(c, 10) * powMod(a, b[index])) % BASE;
    }
    // O(10)
    private int powMod(int a, int m) {
        if (a == 1) {
            return 1;
        }
        int res = 1;
        int pow = a % BASE;
        for (int i = 0; i < m; i++){
            res = (res * pow) % BASE;
        }
        return res;
    }
}
