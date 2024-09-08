/*
 * @lc app=leetcode id=399 lang=java
 *
 * [399] Evaluate Division
 *
 * https://leetcode.com/problems/evaluate-division/description/
 *
 * algorithms
 * Medium (62.12%)
 * Likes:    9345
 * Dislikes: 947
 * Total Accepted:    501.8K
 * Total Submissions: 807.7K
 * Testcase Example:  '[["a","b"],["b","c"]]\n' +
  '[2.0,3.0]\n' +
  '[["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]'
 *
 * You are given an array of variable pairs equations and an array of real
 * numbers values, where equations[i] = [Ai, Bi] and values[i] represent the
 * equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a
 * single variable.
 * 
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the
 * j^th query where you must find the answer for Cj / Dj = ?.
 * 
 * Return the answers to all queries. If a single answer cannot be determined,
 * return -1.0.
 * 
 * Note: The input is always valid. You may assume that evaluating the queries
 * will not result in division by zero and that there is no contradiction.
 * 
 * Note: The variables that do not occur in the list of equations are
 * undefined, so the answer cannot be determined for them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries =
 * [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation: 
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * note: x is undefined => -1.0
 * 
 * Example 2:
 * 
 * 
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values =
 * [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: equations = [["a","b"]], values = [0.5], queries =
 * [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    class Pair {
        String var;
        double val;

        Pair(String var, double val) {
            this.var = var;
            this.val = val;
        }

        @Override
        public String toString() {
            return "[var = " + var + ", val = " + val +"]";
        }
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair>> graph = new HashMap<>();
        //create graph
        for(int i = 0; i < equations.size(); i++) {
            List<String> vars = equations.get(i);
            String var1 = vars.get(0);
            String var2 = vars.get(1);
            double val = values[i];

            List<Pair> var1List = graph.getOrDefault(var1, new ArrayList<Pair>());
            var1List.add(new Pair(var2, val));
            graph.put(var1, var1List);

            List<Pair> var2List = graph.getOrDefault(var2, new ArrayList<Pair>());
            var2List.add(new Pair(var1, 1/val));
            graph.put(var2, var2List);
        }
        double[] result = new double[queries.size()];

        for(int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String from = query.get(0);
            String to = query.get(1);
            double res = findPath(from, to, new HashSet<String>(), graph);
            // System.out.println( "from = " + from + ", to = " + to + ", res = " + res);
            if(res < 0) {
                result[i] = -1;
            }
            else {
                result[i] = res;
                List<Pair> var1List = graph.get(from);
                var1List.add(new Pair(to, res));
                List<Pair> var2List = graph.get(to);
                var2List.add(new Pair(from, 1/res));
            }
        }
        return result;
    }

    double findPath(String from, String toFind, Set<String> visited, Map<String, List<Pair>> graph) {
        // System.out.println("from = " + from + " toFind = " + toFind);
        if(from.equals(toFind) && graph.containsKey(from)) return 1;
        // System.out.println("Not return");

        visited.add(from);

        List<Pair> neibors = graph.get(from);
        if(neibors == null) {
            return -1;
        }

        double result;
        // System.out.println("from " + from + " neighbors "+ neibors);
        for(Pair p: neibors) {
            if(!visited.contains(p.var)) {
                // System.out.println("from " + from + " to: "+ p);
                result = findPath(p.var, toFind, visited, graph);
                // System.out.println("Result from " + from + " result to:  " + p + " cur = " +  result + " " + p.val *  result);
                result = p.val *  result;
                if(result >= 0) {
                    return result;
                }
            }
        }
        return -1;
    }
}
// @lc code=end

