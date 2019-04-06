package heap;

/* 692. Top K Frequent Words
Description:
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.

Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 */

import java.util.*;

public class TopKFrequentWords {
    // Solution1: Bucket Sort
    // Time Complexity: O(n)
    // Space Complexity: O(n) (HashMap + buckets)
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String w: words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        List<String>[] freq = new List[words.length + 1];
        for (String key: map.keySet()) {
            int f = map.get(key);
            if (freq[f] == null) {
                freq[f] = new ArrayList<String>();
            }
            freq[f].add(key);
        }
        for (int i = freq.length - 1; i >= 0 && res.size() < k; --i) {
            if (freq[i] != null) {
                Collections.sort(freq[i]);
                res.addAll(freq[i]);
            }
        }
        return res.size() == k ? res : res.subList(0, k);
    }

    // Solution2: Min Heap
    // Time Complexity: O(n log k)
    // Space Complexity: O(n) (HashMap)
    public List<String> topKFrequent1(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String w: words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (!map.get(a).equals(map.get(b))) {
                return map.get(a) - map.get(b);
            } else {
                return b.compareTo(a);
            }
        });
        for (String s: map.keySet()) {
            pq.offer(s);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
