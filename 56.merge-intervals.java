/*
 * @lc app=leetcode id=56 lang=java
 *
 * [56] Merge Intervals
 *
 * https://leetcode.com/problems/merge-intervals/description/
 *
 * algorithms
 * Medium (47.85%)
 * Likes:    22404
 * Dislikes: 791
 * Total Accepted:    2.6M
 * Total Submissions: 5.4M
 * Testcase Example:  '[[1,3],[2,6],[8,10],[15,18]]'
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all
 * overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into
 * [1,6].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        LinkedList<int[]> res = new LinkedList<>();

        res.add(intervals[0]);

        for(int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            int[] top = res.getLast();
            if(cur[0] <= top[1]) {
                top[1] = Math.max(cur[1], top[1]);
            } else {
                res.add(cur);
            }
        }
        int[][] intRes = new int[res.size()][];
        return res.toArray(intRes);
    }
}
// @lc code=end

