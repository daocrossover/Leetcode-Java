package math;

/* 537. Complex Number Multiplication
Description:
Given two strings representing two complex numbers.

You need to return a string representing their multiplication.
Note i^2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.

Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.

Note:
1. The input strings will not have extra blank.
2. The input strings will be given in the form of a+bi,
where the integer a and b will both belong to the range of [-100, 100].
And the output should be also in this form.
 */

public class ComplexNumberMultiplication {
    public String complexNumberMultiply(String a, String b) {
        String[] A = a.split("\\+");
        String[] B = b.split("\\+");
        int a1 = Integer.parseInt(A[0]);
        int a2 = Integer.parseInt(A[1].replace("i",""));
        int b1 = Integer.parseInt(B[0]);
        int b2 = Integer.parseInt(B[1].replace("i",""));
        String real = a1 * b1 - a2 * b2 + "";
        String imaginary = a1 * b2 + a2 * b1 + "i";
        return real + "+" + imaginary;
    }
}
