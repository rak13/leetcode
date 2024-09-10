/*
 * @lc app=leetcode id=2334 lang=java
 *
 * [2334] Subarray With Elements Greater Than Varying Threshold
 *
 * https://leetcode.com/problems/subarray-with-elements-greater-than-varying-threshold/description/
 *
 * algorithms
 * Hard (43.26%)
 * Likes:    557
 * Dislikes: 8
 * Total Accepted:    11.1K
 * Total Submissions: 25.4K
 * Testcase Example:  '[1,3,4,3,1]\n6'
 *
 * You are given an integer array nums and an integer threshold.
 * 
 * Find any subarray of nums of length k such that every element in the
 * subarray is greater than threshold / k.
 * 
 * Return the size of any such subarray. If there is no such subarray, return
 * -1.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,4,3,1], threshold = 6
 * Output: 3
 * Explanation: The subarray [3,4,3] has a size of 3, and every element is
 * greater than 6 / 3 = 2.
 * Note that this is the only valid subarray.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [6,5,6,5,8], threshold = 7
 * Output: 1
 * Explanation: The subarray [8] has a size of 1, and 8 > 7 / 1 = 7. So 1 is
 * returned.
 * Note that the subarray [6,5] has a size of 2, and every element is greater
 * than 7 / 2 = 3.5. 
 * Similarly, the subarrays [6,5,6], [6,5,6,5], [6,5,6,5,8] also satisfy the
 * given conditions.
 * Therefore, 2, 3, 4, or 5 may also be returned.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], threshold <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
        Keep monotonic increasing stack,
        The previoius top val in stack applies from it's previous val to now
        stackVals = [(0,1), (2, 4), ] => (pos, val)
        curVal = (3,3)

        then, we can say (2,4), value of 4 is min from after (0,1) to before (3,3) =>Range =  3 - 0 - 1 = 2, 
        i.e From indeces [1,2], 4 is minimum value. 


     */
    public int validSubarraySize(int[] nums, int threshold) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;
        int k = 0;
        for (int i = 0; i <= n; i++) {
            int val = i == n ? 0 : nums[i];
            while(!stack.isEmpty() && nums[stack.peek()] >= val) {
                int lastMinPos = stack.pop();
                int lastMinVal = nums[lastMinPos];
                int prevMinPos = stack.isEmpty() ? -1 : stack.peek();

                k = i - prevMinPos - 1;

                if((double)lastMinVal > (double)(threshold/k)) {
                    return k;
                }
            }
            stack.push(i);
        }
        return -1;
    }
}
// @lc code=end

