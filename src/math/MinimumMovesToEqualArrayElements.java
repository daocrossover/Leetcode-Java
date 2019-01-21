package math;

/* 453. Minimum Moves to Equal Array Elements
Description:
Given a non-empty integer array of size n,
find the minimum number of moves required to make all array elements equal,
where a move is incrementing n - 1 elements by 1.

Example:
Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */

public class MinimumMovesToEqualArrayElements {
    // sum + (n-1) * m = x * n
    // minNum + m = x
    // m = sum - n * minNum
    public int minMoves(int[] nums) {
        int sum = 0, minNum = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            if (num < minNum) {
                minNum = num;
            }
        }
        return sum - nums.length * minNum;
    }
}
