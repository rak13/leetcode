/*
 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */

// @lc code=start
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int p1 = 0;
        int p2 = 0;
        int sum = 0;
        int range = Integer.MAX_VALUE; 
        while(p2 < nums.length) {
            if(sum < target) {
                sum+=nums[p2++];
            } 
            while(sum >= target){
                range = Math.min(range, p2 - p1);
                sum -= nums[p1++];
            }
        }
        return (range == Integer.MAX_VALUE) ? 0 : range;
    }
}
// @lc code=end

