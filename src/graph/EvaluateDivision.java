package graph;

/* 399. Evaluate Division
Description:
Equations are given in the format A / B = k, where A and B are variables represented as strings,
and k is a real number (floating point number). Given some queries, return the answers.
If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values,
vector<pair<string, string>> queries , where equations.size() == values.size(),
and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
The input is always valid. You may assume that evaluating the queries will result in no division
by zero and there is no contradiction.
 */

import java.util.HashMap;
import java.util.HashSet;

public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // build the map
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; ++i) {
            String var1 = equations[i][0];
            String var2 = equations[i][1];
            double val = values[i];
            graph.putIfAbsent(var1, new HashMap<String, Double>());
            graph.putIfAbsent(var2, new HashMap<String, Double>());
            graph.get(var1).put(var2, val);
            graph.get(var2).put(var1, 1 / val);
        }
        // search dividend and divisor using DFS
        double res[] = new double[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            String var1 = queries[i][0];
            String var2 = queries[i][1];
            // if dividend and divisor don't exist, return -1.0
            if (!graph.containsKey(var1) || !graph.containsKey(var2)) {
                res[i] = -1.0;
            } else {
                HashSet<String> visited = new HashSet<>();
                visited.add(var1);
                res[i] = dfs(var1, var2, visited, graph);
            }
        }
        return res;
    }

    public double dfs(String start, String end, HashSet<String> visited, HashMap<String, HashMap<String, Double>> graph) {
        if (start.equals(end)) {
            return 1.0;
        }
        HashMap<String, Double> m = graph.get(start);
        for (String key : m.keySet()) {
            if (!visited.contains(key)) {
                visited.add(key);
                double res = dfs(key, end, visited, graph);
                // a / b = xx, b / c = yy, then a / c = xx * yy
                if (res != -1.0) {
                    return m.get(key) * res;
                }
                visited.remove(key);
            }
        }
        // if the current node has been visited or we can't find a path
        return -1.0;
    }
}
