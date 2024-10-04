/*
 * @lc app=leetcode id=416 lang=java
 *
 * [416] Partition Equal Subset Sum
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (46.67%)
 * Likes:    12445
 * Dislikes: 255
 * Total Accepted:    938.7K
 * Total Submissions: 2M
 * Testcase Example:  '[1,5,11,5]'
 *
 * Given an integer array nums, return true if you can partition the array into
 * two subsets such that the sum of the elements in both subsets is equal or
 * false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    int N;
    int[] nums;
    Boolean[][] dp;
    int total = 0;
    int maxHalfSum = 0;
    public boolean canPartition(int[] nums) {
        this.nums = nums;
        N = nums.length;

        total = 0;
        for(int i = 0; i < nums.length; i++) {
            total += nums[i];
        }

        if(total % 2 != 0) return false;

        maxHalfSum = total / 2;

        dp = new Boolean[N][total+1];
        return canPart(0, 0);
    }

    boolean canPart(int pos, int partASum) {
        if(partASum >= maxHalfSum || pos == N) {
            return partASum == total - partASum;
        }
        if(dp[pos][partASum] == null) {
            boolean way1 = false, way2 = false;
            way1 = canPart(pos + 1, partASum);
            if(way1) {
                dp[pos][partASum] = way1;
                //early return
                return true;
            } 

            way2 = canPart(pos + 1, partASum + nums[pos]);
            dp[pos][partASum] = way2;
        }
       
        return dp[pos][partASum];
    }
}
// @lc code=end

