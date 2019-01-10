package bit_manipulation;

/* 405. Convert a Number to Hexadecimal
Description:
Given an integer, write an algorithm to convert it to hexadecimal.
For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s.
If the number is zero, it is represented by a single zero character '0';
otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.

Example 1:
Input:
26
Output:
"1a"

Example 2:
Input:
-1
Output:
"ffffffff"
 */

/* Solution:
每次取最低四位（换成二进制）转换为十六进制，再向右移四位。
使用>>>而不是>>，>>>无符号右移，忽略符号位，空位都以0补齐
-15 = 11110001
-15 >> 2 = 11111100
-15 >>> 2 = 00111100
 */

public class ConvertANumberToHexadecimal {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        String res = "";
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        while (num != 0) {
            res = map[num & 15] + res;
            num >>>= 4;
        }
        return res;
    }
}
