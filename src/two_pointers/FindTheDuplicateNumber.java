package two_pointers;

/* 287. Find the Duplicate Number
Description:
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
prove that at least one duplicate number must exist.
Assume that there is only one duplicate number, find the duplicate one.

Example 1:
Input: [1,3,4,2,2]
Output: 2

Example 2:
Input: [3,1,3,4,2]
Output: 3

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.
 */

public class FindTheDuplicateNumber {
    // Solution1: Two Pointers, fast and slow
    // Like the problem Linked List Cycle II
    // Time Complexity: O(n), Space Complexity: O(1)
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        // find the intersection point of the two pointers.
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // find the "entrance" to the cycle.
        int entry = nums[0];
        while (entry != slow) {
            entry = nums[entry];
            slow = nums[slow];
        }
        return entry;
    }

    // Solution2: Binary Search
    // Time Complexity: O(nlogn), Space Complexity: O(1)
    public int findDuplicate1(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int num: nums) {
                if (num <= mid) {
                    count++;
                }
            }
            // if the count is less or equal to mid,
            // the duplicate number should in the range [mid + 1, high]
            if (count <= mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Solution3: If we can modify the array
    public int findDuplicate2(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] != i+1) {
                if (nums[i] == nums[nums[i]-1]) {
                    return nums[i];
                }
                int t = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = t;
            }
        }
        return -1;
    }
}
