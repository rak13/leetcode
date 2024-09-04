/*
 * @lc app=leetcode id=554 lang=java
 *
 * [554] Brick Wall
 *
 * https://leetcode.com/problems/brick-wall/description/
 *
 * algorithms
 * Medium (55.28%)
 * Likes:    2534
 * Dislikes: 172
 * Total Accepted:    144.9K
 * Total Submissions: 262.2K
 * Testcase Example:  '[[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]'
 *
 * There is a rectangular brick wall in front of you with n rows of bricks. The
 * i^th row has some number of bricks each of the same height (i.e., one unit)
 * but they can be of different widths. The total width of each row is the
 * same.
 * 
 * Draw a vertical line from the top to the bottom and cross the least bricks.
 * If your line goes through the edge of a brick, then the brick is not
 * considered as crossed. You cannot draw a line just along one of the two
 * vertical edges of the wall, in which case the line will obviously cross no
 * bricks.
 * 
 * Given the 2D array wall that contains the information about the wall, return
 * the minimum number of crossed bricks after drawing such a vertical line.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: wall = [[1],[1],[1]]
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == wall.length
 * 1 <= n <= 10^4
 * 1 <= wall[i].length <= 10^4
 * 1 <= sum(wall[i].length) <= 2 * 10^4
 * sum(wall[i]) is the same for each row i.
 * 1 <= wall[i][j] <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        return wall.size() - countMaxGapsAtPosition(wall);
    }

    int countMaxGapsAtPosition(List<List<Integer>> wall) {
        Map<Integer, Integer> M = new HashMap<>();

        for(List<Integer> row: wall) {
            int pos = 0;
            for(int i = 0;  i < row.size() - 1; i++) {
                int brickSize = row.get(i);
                pos += brickSize;
                
                M.put(pos, M.getOrDefault(pos, 0) + 1);
                // System.out.printf("pos =  %s, count = %s\n" , pos,  M.get(pos));
            }
        }

        Integer maxGaps = 0;

        for(Integer val: M.values()) {
            maxGaps = Math.max(maxGaps, val);
        }
        return maxGaps;
    }
}
// @lc code=end

