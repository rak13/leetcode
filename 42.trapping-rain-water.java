/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 *
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (62.91%)
 * Likes:    32407
 * Dislikes: 535
 * Total Accepted:    2.3M
 * Total Submissions: 3.7M
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it can trap after raining.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array
 * [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue
 * section) are being trapped.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 * 
 * 
 */


/**
 * The intuition, at a given index, the min(left-max, right max) can hold the water
 * if(leftMax < rightMax), water will spill from the leftMax
 *      - so calcualate the current left move right
 * if(rightMax < leftMax) water will spill over the rightMax 
 *      - so calculate the current right and move left
 * 
 * 
 * Other strategies:
 * precalculate leftMax, and rightMax - keep them arrays
 *  - for each poistion use it's leftMax and rightMax to find how much water it can hold
 */



// @lc code=start
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;

        int leftMax = height[left], rightMax = height[right];
        int curAns = 0;
        while(left <= right) {
            if(leftMax < rightMax) {
                leftMax = Math.max(leftMax, height[left]);
                curAns = Math.min(leftMax, rightMax) - height[left];
                // System.out.println("LEFT!! = left = " +  left + ", r = " + right + ", lMax " + leftMax + ", rMax " + rightMax + ", curAns = " + curAns);
                left++;        
            } else {
                
                rightMax = Math.max(rightMax, height[right]);
                curAns =  Math.min(leftMax, rightMax) - height[right];
                // System.out.println("RIGHT!! = left = " +  left + ", h[left] = " + height[left] + ", r = " + right + ", h[right] = " + height[right] + ", lMax " + leftMax + ", rMax " + rightMax + ", curAns = " + curAns);
                right--;
            }
            curAns = curAns < 0 ? 0 : curAns;
            ans += curAns;
            // System.out.println(" curAns = " + curAns + ", ans = " + ans);
        }

        return ans;
    }
}
// @lc code=end

