package heap;

/* 215. Kth Largest Element in an Array
Description:
Find the kth largest element in an unsorted array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Arrays;

public class KthLargestElementInAnArray {
    // Solution1: Using min heap
    // Maintain a min heap to store k elements,
    // and the top of the heap is the kth largest element in the end
    // Time Complexity: O(n log k)
    // Space Complexity: O(k)
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; ++i) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    // Solution2: Using max heap
    // Maintain a max heap to store k elements, push all elements in the nums to the max heap
    // and poll() k-1 times, then the top of the heap is the kth largest element in the end
    // Time Complexity: O(k log n)
    // Space Complexity: O(n)
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                return j - i;
            }
        });

        for (int num: nums) {
            pq.offer(num);
        }
        for (int i = 0; i < k - 1; ++i) {
            pq.poll();
        }
        return pq.peek();
    }

    // Solution3: Using partition in QuickSort
    // select a pivot, put the element which is less than pivot to the left of pivot
    // put the element which is larger than pivot to the right of pivot
    // Time Complexity: O(n) (best case)
    //                  O(n^2) (worst case)
    // If using shuffle first, the time complexity could be O(n)
    // Space Complexity: O(1)
    public int findKthLargest2(int[] nums, int k) {
        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        while (lo < hi) {
            while (lo < hi && nums[hi] >= pivot) --hi;
            nums[lo] = nums[hi];
            while (lo < hi && nums[lo] <= pivot) ++lo;
            nums[hi] = nums[lo];
        }
        nums[lo] = pivot;
        return lo;
    }

    private void shuffle(int nums[]) {
        Random random = new Random();
        for (int i = 1; i < nums.length; i++) {
            int r = random.nextInt(i + 1);
            swap(nums, i, r);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Solution4: Sorting
    // Time Complexity: O(n log n)
    // Space Complexity: O(1)
    public int findKthLargest3(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }
}
