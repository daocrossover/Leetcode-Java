package divide_and_conquer;

/* 241. Different Ways to Add Parentheses
Description:
Given a string of numbers and operators,
return all possible results from computing all the different possible ways to group numbers and operators.
The valid operators are +, - and *.

Example 1:
Input: "2-1-1"
Output: [0, 2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2

Example 2:
Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentWaysToAddParentheses {
    // Memoization
    Map<String, List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (map.containsKey(input)) {
            return map.get(input);
        }
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                // Divide and Conquer
                // Split input string into two parts and solve them recursively
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> res1 = diffWaysToCompute(part1);
                List<Integer> res2 = diffWaysToCompute(part2);
                for (Integer num1: res1) {
                    for (Integer num2: res2) {
                        if (c == '+') {
                            res.add(num1 + num2);
                        } else if (c == '-') {
                            res.add(num1 - num2);
                        } else {
                            res.add(num1 * num2);
                        }
                    }
                }
            }
        }
        // if the input string contains only number
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        map.put(input, res);
        return res;
    }
}
