/*
 * @lc app=leetcode id=1466 lang=java
 *
 * [1466] Reorder Routes to Make All Paths Lead to the City Zero
 *
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/
 *
 * algorithms
 * Medium (64.52%)
 * Likes:    4200
 * Dislikes: 125
 * Total Accepted:    208.3K
 * Total Submissions: 322.8K
 * Testcase Example:  '6\n[[0,1],[1,3],[2,3],[4,0],[4,5]]'
 *
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there
 * is only one way to travel between two different cities (this network form a
 * tree). Last year, The ministry of transport decided to orient the roads in
 * one direction because they are too narrow.
 * 
 * Roads are represented by connections where connections[i] = [ai, bi]
 * represents a road from city ai to city bi.
 * 
 * This year, there will be a big event in the capital (city 0), and many
 * people want to travel to this city.
 * 
 * Your task consists of reorienting some roads such that each city can visit
 * the city 0. Return the minimum number of edges changed.
 * 
 * It's guaranteed that each city can reach city 0 after reorder.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node
 * can reach the node 0 (capital).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node
 * can reach the node 0 (capital).
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 5 * 10^4
 * connections.length == n - 1
 * connections[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minReorder(int n, int[][] connections) {

        boolean[] visited = new boolean[n];

        Map<Integer, Set<Integer>> dirGraph = new HashMap<>();
        Map<Integer, Set<Integer>> unDirGraph = new HashMap<>();

        for(int i = 0; i < n; i++) {
            dirGraph.put(i, new HashSet<Integer>());
            unDirGraph.put(i, new HashSet<Integer>());
        }

        for (int[] con : connections) {
            int u = con[0];
            int v = con[1];

            Set<Integer> dirConns = dirGraph.get(u);
            Set<Integer> unDirConnsU = unDirGraph.get(u);
            Set<Integer> unDirConnsV = unDirGraph.get(v);

            dirConns.add(v);
            unDirConnsU.add(v);
            unDirConnsV.add(u);
        }
        visited[0] = true;

        return findRoadChanges(0, visited, unDirGraph, dirGraph);
    }

    int findRoadChanges(int u, boolean[] visited, Map<Integer, Set<Integer>> unDirGraph,
            Map<Integer, Set<Integer>> dirGraph) {
        Set<Integer> dirConns = dirGraph.get(u);
        Set<Integer> unDirConnsU = unDirGraph.get(u);

        int revCounts = 0;
        for (Integer v : unDirConnsU) {
            if (!visited[v]) {
                visited[v] = true;

                if (dirConns.contains(v)) {
                    revCounts++;
                }
                revCounts += findRoadChanges(v, visited, unDirGraph, dirGraph);
            }
        }

        return revCounts;
    }
}
// @lc code=end

