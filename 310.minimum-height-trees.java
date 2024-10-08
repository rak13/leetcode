/*
 * @lc app=leetcode id=310 lang=java
 *
 * [310] Minimum Height Trees
 *
 * https://leetcode.com/problems/minimum-height-trees/description/
 *
 * algorithms
 * Medium (41.84%)
 * Likes:    8336
 * Dislikes: 386
 * Total Accepted:    393.9K
 * Total Submissions: 940.5K
 * Testcase Example:  '4\n[[1,0],[1,2],[1,3]]'
 *
 * A tree is an undirected graph in which any two vertices are connected by
 * exactly one path. In other words, any connected graph without simple cycles
 * is a tree.
 * 
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1
 * edges where edges[i] = [ai, bi] indicates that there is an undirected edge
 * between the two nodes ai and bi in the tree, you can choose any node of the
 * tree as the root. When you select a node x as the root, the result tree has
 * height h. Among all possible rooted trees, those with minimum height (i.e.
 * min(h))  are called minimum height trees (MHTs).
 * 
 * Return a list of all MHTs' root labels. You can return the answer in any
 * order.
 * 
 * The height of a rooted tree is the number of edges on the longest downward
 * path between the root and a leaf.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node
 * with label 1 which is the only MHT.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 2 * 10^4
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated
 * edges.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n < 2) {
            ArrayList<Integer> centroids = new ArrayList<>();
            for (int i = 0; i < n; i++)
                centroids.add(i);
            return centroids;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            Integer u = edge[0], v = edge[1];
            if(!graph.containsKey(u)) {
                graph.put(u, new HashSet<Integer>());
            }
            if(!graph.containsKey(v)) {
                graph.put(v, new HashSet<Integer>());
            }
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        List<Integer> leaves = new ArrayList<>();
        for(int u = 0; u < n; u++){
            var neighbors = graph.get(u);
            if(neighbors.size() == 1) {
                leaves.add(u);
            }
        };

        int remainingNodes = n;
        while(remainingNodes > 2) {
            remainingNodes -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();

            for(Integer u : leaves) {
                var neighbors = graph.get(u);
                for(var v : neighbors) {//there will be only one neighbor
                    graph.get(v).remove(u);
                    if(graph.get(v).size() == 1) { //add only when v becomes a leaf!
                        newLeaves.add(v);
                    }
                }
            }
            leaves = newLeaves;
        }

        return leaves;
    }
}
// @lc code=end

