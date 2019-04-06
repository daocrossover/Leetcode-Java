package bfs;

/* 127. Word Ladder
Description:
Given two words (beginWord and endWord), and a dictionary's word list,
find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Example 1:
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Example 2:
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

import java.util.*;

public class WordLadder {
    // Solution1: BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        if (!dict.contains(endWord)) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int ladder = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                String word = q.poll();
                char[] str = word.toCharArray();
                // try all possible substitution (26 characters) in every position of current word
                for (int j = 0; j < str.length; ++j) {
                    char c = str[j];
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        str[j] = ch;
                        String newWord = String.valueOf(str);
                        if (newWord.equals(endWord) && dict.contains(endWord)) {
                            return ladder + 1;
                        }
                        // add possible word for next ladder (level)
                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            q.offer(newWord);
                            visited.add(newWord);
                        }
                    }
                    str[j] = c;
                }
            }
            ladder++;
        }
        return 0;
    }

    // Solution2: Two-end BFS
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        int ladder = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // swap two sets to make beginSet smaller
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            Set<String> tmp = new HashSet<>();
            for (String word: beginSet) {
                char[] str = word.toCharArray();
                for (int j = 0; j < str.length; ++j) {
                    char c = str[j];
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        str[j] = ch;
                        String newWord = String.valueOf(str);
                        if (endSet.contains(newWord)) {
                            return ladder + 1;
                        }
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            tmp.add(newWord);
                            visited.add(newWord);
                        }
                    }
                    str[j] = c;
                }
            }
            beginSet = tmp;
            ladder++;
        }
        return 0;
    }
}
