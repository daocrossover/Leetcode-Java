package math;

/* 204. Count Primes
Description:
Count the number of prime numbers less than a non-negative number, n.

Example:
Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

Solution:
Sieve of Eratosthenes
 */

/* 思路
首先初始化bool数组全为true，然后从i=2开始遍历，i的所有倍数均不是质数，
j从i*i开始，因为3*2已经在2*3时赋值过了，从3*3开始就可以。
 */

import java.util.Arrays;

public class CountPrimes {
    public int countPrimes(int n) {
        if (n <= 2) return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; ++i) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}
