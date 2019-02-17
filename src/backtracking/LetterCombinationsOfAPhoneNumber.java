package backtracking;

/* 17. Letter Combinations of a Phone Number
Description:
Given a string containing digits from 2-9 inclusive,
return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
Note that 1 does not map to any letters.

Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order,
your answer could be in any order you want.
 */

import java.util.LinkedList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    // Solution1: backtracking
    private String[] charMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private void backtrack(List<String> res, String digits, String cur, int index) {
        if (index == digits.length()) {
            res.add(cur);
            return;
        }
        String letters = charMap[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); ++i) {
            backtrack(res, digits, cur + letters.charAt(i), index + 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits != null && digits.length() != 0) {
            backtrack(res, digits, "", 0);
        }
        return res;
    }

    // Solution2: Iterative
    public List<String> letterCombinations1(String digits) {
        List<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) return res;
        res.add("");
        char[][] map = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        for (int i = 0; i< digits.length(); ++i) {
            List<String> nextList = new LinkedList<>();
            int num = digits.charAt(i) - '0';
            for (String s: res) {
                for (int j = 0; j < map[num].length; ++j) {
                    nextList.add(s + map[num][j]);
                }
            }
            res = nextList;
        }
        return res;
    }
}
