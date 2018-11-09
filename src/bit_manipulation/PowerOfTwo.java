package bit_manipulation;

public class PowerOfTwo {
    // Solution1: Iterative
    // check if n can be divided by 2.
    // Time Complexity: O(log n)
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        while (n > 1) {
            if ((n & 1) != 0) {
                return false;
            }
            n >>= 1;
        }
        return true;
    }

    // Solution2: Bit operation
    // If n is the power of two:
    // n = 2 ^ 0 = 1 = 0b0000...00000001, and (n - 1) = 0 = 0b0000...0000.
    // n = 2 ^ 1 = 2 = 0b0000...00000010, and (n - 1) = 1 = 0b0000...0001.
    // n = 2 ^ 2 = 4 = 0b0000...00000100, and (n - 1) = 3 = 0b0000...0011.
    // n = 2 ^ 3 = 8 = 0b0000...00001000, and (n - 1) = 7 = 0b0000...0111.
    // we have n & (n-1) == 0b0000...0000 == 0
    // Otherwise, n & (n-1) != 0.
    public boolean isPowerOfTwo1(int n) {
        return n > 0 && ((n & (n-1)) == 0);
    }

    // Solution3: Bit count
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
