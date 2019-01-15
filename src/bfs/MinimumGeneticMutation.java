package bfs;

/* 433. Minimum Genetic Mutation
Description:
A gene string can be represented by an 8-character long string,
with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"),
where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations.
A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine
what is the minimum number of mutations needed to mutate from "start" to "end".
If there is no such a mutation, return -1.

Note:
Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.

Example 1:
start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]
return: 1

Example 2:
start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
return: 2

Example 3:
start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
return: 3
 */

import java.util.*;

public class MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        char[] gene = {'A', 'C', 'G', 'T'};
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int level = 0;
        q.offer(start);
        visited.add(start);
        // BFS
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                String cur = q.poll();
                if (cur.equals(end)) {
                    return level;
                }
                char[] curArray = cur.toCharArray();
                // try each char of the current gene
                for (int j = 0; j < curArray.length; ++j) {
                    char old = curArray[j];
                    for (char g : gene) {
                        curArray[j] = g;
                        String tmp = new String(curArray);
                        if (!visited.contains(tmp) && bankSet.contains(tmp)) {
                            q.offer(tmp);
                            visited.add(tmp);
                        }
                    }
                    curArray[j] = old;
                }
            }
            level++;
        }
        return -1;
    }
}
