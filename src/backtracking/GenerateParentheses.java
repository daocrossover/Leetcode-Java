package backtracking;

/* 22. Generate Parentheses
Description:
Given n pairs of parentheses, write a function to generate all
combinations of well-formed parentheses.

For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses {
    // Solution1: backtracking
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, String cur, int open, int close, int max) {
        if (cur.length() == 2 * max) {
            res.add(cur);
            return;
        }
        if (open < max) {
            backtrack(res, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(res, cur + ")", open, close + 1, max);
        }
    }

    // Solution2: Closure Number
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            res.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left : generateParenthesis(c)) {
                    for (String right : generateParenthesis(n - 1 - c)) {
                        res.add("(" + left + ")" + right);
                    }
                }
            }
        }
        return res;
    }
}
