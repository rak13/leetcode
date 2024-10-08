/*
 * @lc app=leetcode id=1245 lang=java
 *
 * [1245] Tree Diameter
 *
 * https://leetcode.com/problems/tree-diameter/description/
 *
 * algorithms
 * Medium (61.19%)
 * Likes:    858
 * Dislikes: 22
 * Total Accepted:    47.5K
 * Total Submissions: 77.6K
 * Testcase Example:  '[[0,1],[0,2]]'
 *
 * The diameter of a tree is the number of edges in the longest path in that
 * tree.
 * 
 * There is an undirected tree of n nodes labeled from 0 to n - 1. You are
 * given a 2D array edges where edges.length == n - 1 and edges[i] = [ai, bi]
 * indicates that there is an undirected edge between nodes ai and bi in the
 * tree.
 * 
 * Return the diameter of the tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [[0,1],[0,2]]
 * Output: 2
 * Explanation: The longest path of the tree is the path 1 - 0 - 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
 * Output: 4
 * Explanation: The longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == edges.length + 1
 * 1 <= n <= 10^4
 * 0 <= ai, bi < n
 * ai != bi
 * 
 * 
 */

// @lc code=start
class Solution {
    Map <Integer, List<Integer>> graph;

    int furthestMode = -1;
    int maxDist = 0;

    public int treeDiameter(int[][] edges) {
        Map <Integer, List<Integer>> graph = new HashMap<>();
        int lastU = 0;
        for(int[] edge : edges) {
            int u = edge[0];
            lastU = u;
            int v = edge[1];
            if(!graph.containsKey(u)) {
                graph.put(u, new ArrayList<>());
            }
            if(!graph.containsKey(v)) {
                graph.put(v, new ArrayList<>());
            }

            List<Integer> neigU = graph.get(u);
            List<Integer> neigV = graph.get(v);

            neigU.add(v);
            neigV.add(u);
        }

        this.graph = graph;

        Map<Integer, Integer> visited = new HashMap<>();
        visited.put(lastU, 0);
        findFurthestWithDfs(lastU, visited);

        int initialFurthestNode = furthestMode;

        furthestMode = -1;
        maxDist = 0;


        visited = new HashMap<>();
        visited.put(initialFurthestNode, 0);
        findFurthestWithDfs(initialFurthestNode, visited);
        
        return maxDist;
    }

    void findFurthestWithDfs(int u, Map<Integer, Integer> visited) {
        int distU = visited.get(u);
        if(distU > maxDist) {
            furthestMode = u;
            maxDist = distU;
        }

        List<Integer> neigU = graph.getOrDefault(u, new ArrayList<Integer>());
        for(Integer v: neigU) {
            if(!visited.containsKey(v)) {
                visited.put(v, distU + 1);
                findFurthestWithDfs(v, visited);
            }
        }
    }
}
// @lc code=end

