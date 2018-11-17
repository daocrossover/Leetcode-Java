/* 386. Lexicographical Numbers
Description:
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space.
The input size may be as large as 5,000,000.
 */

import java.util.ArrayList;
import java.util.List;

public class LexicographicalNumbers {
    // Solution1: Iterative Solution
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>(n);
        int cur = 1;
        for (int i = 0; i < n; ++i) {
            res.add(cur);
            if (cur * 10 <= n) {
                cur *= 10;
            } else if (cur % 10 != 9 && cur + 1 <= n) {
                cur++;
            } else {
                while (cur % 10 == 9 || cur == n) {
                    cur /= 10;
                }
                cur++;
            }
        }
        return res;
    }

    // Solution2: DFS
    public List<Integer> lexicalOrder1(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(i, n, res);
        }
        return res;
    }

    public void dfs(int cur, int n, List<Integer> res) {
        if (cur > n) {
            return;
        } else{
            res.add(cur);
            for (int i = 0; i < 10; ++i) {
                if (10*cur+i > n) {
                    return;
                }
                dfs(10*cur+i, n, res);
            }
        }
    }
}
