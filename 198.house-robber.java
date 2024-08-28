/*
 * @lc app=leetcode id=198 lang=java
 *
 * [198] House Robber
 *
 * https://leetcode.com/problems/house-robber/description/
 *
 * algorithms
 * Medium (51.42%)
 * Likes:    21220
 * Dislikes: 432
 * Total Accepted:    2.4M
 * Total Submissions: 4.7M
 * Testcase Example:  '[1,2,3,1]'
 *
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping
 * you from robbing each of them is that adjacent houses have security systems
 * connected and it will automatically contact the police if two adjacent
 * houses were broken into on the same night.
 * 
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the
 * police.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house
 * 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * 
 * 
 */

// @lc code=start
class Solution {
    Integer[] mem;

    public int rob(int[] nums) {
        if(nums == null) return 0;
        mem = new Integer[nums.length + 1];
        return robberHelper(0, nums);
    }

    int robberHelper(int pos, int[] nums) {
        if(pos >= nums.length) {
            return 0;
        }

        if(mem[pos] == null) {
            int way1 = nums[pos] + robberHelper(pos + 2, nums);
            int way2 = robberHelper(pos + 1, nums);
            mem[pos] = Math.max(way1, way2);
        }
        return mem[pos];
    }
}
// @lc code=end

