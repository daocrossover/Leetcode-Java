package heap;

/* 347. Top K Frequent Elements
Description:
Given a non-empty array of integers, return the k most frequent elements.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

import java.util.*;

public class TopKFrequentElements {
    // Solution1: Bucket Sort
    // Time Complexity: O(n), Space Complexity: O(n) (except hashMap)
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (bucket[value] == null) {
                bucket[value] = new ArrayList<>();
            }
            bucket[value].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && res.size() < k; --i) {
            if (bucket[i] != null) {
                res.addAll(bucket[i]);
            }
        }
        return res;
    }

    // Solution2: Min Heap
    // Time Complexity: O(n log k), Space Complexity: O(k) (except hashMap)
    public List<Integer> topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }

    // Solution3: Max Heap
    // Time Complexity: O(k log n), Space Complexity: O(n) (except hashMap)
    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            maxHeap.offer(entry);
        }
        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }

    // Solution4: Tree Map
    public List<Integer> topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (!freqMap.containsKey(value)) {
                freqMap.put(value, new LinkedList<>());
            }
            freqMap.get(value).add(key);
        }
        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }
}
