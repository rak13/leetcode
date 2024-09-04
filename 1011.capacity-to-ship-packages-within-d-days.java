/*
 * @lc app=leetcode id=1011 lang=java
 *
 * [1011] Capacity To Ship Packages Within D Days
 *
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
 *
 * algorithms
 * Medium (70.25%)
 * Likes:    9565
 * Dislikes: 231
 * Total Accepted:    426.7K
 * Total Submissions: 607.5K
 * Testcase Example:  '[1,2,3,4,5,6,7,8,9,10]\n5'
 *
 * A conveyor belt has packages that must be shipped from one port to another
 * within days days.
 * 
 * The i^th package on the conveyor belt has a weight of weights[i]. Each day,
 * we load the ship with packages on the conveyor belt (in the order given by
 * weights). We may not load more weight than the maximum weight capacity of
 * the ship.
 * 
 * Return the least weight capacity of the ship that will result in all the
 * packages on the conveyor belt being shipped within days days.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages
 * in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * 
 * Note that the cargo must be shipped in the order given, so using a ship of
 * capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6,
 * 7), (8), (9), (10) is not allowed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in
 * 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= days <= weights.length <= 5 * 10^4
 * 1 <= weights[i] <= 500
 * 
 * 
 */

/** 
 * 
 * 1 1 1 1 3
 * 
*/

// @lc code=start
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int total = 0; 
        int maxW = 0;
        for(int w: weights) {
            total += w;
            maxW = Math.max(maxW, w);
        }
        
        int low = maxW, hi = total, mid;
        while(low < hi) {
            mid = (low + hi) / 2;
            boolean isFeasible = checkFeasible(mid, weights, days);
            if(isFeasible) {
                hi = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return hi;
    }

    boolean checkFeasible(int cap, int[] weights, int days) {
        int wSoFar = 0;
        int i = 0;
        while(i < weights.length && days > 0) {
            wSoFar += weights[i];
            if(wSoFar > cap) {
                wSoFar = weights[i];
                days--;
            }
            i++;
        }
        return days > 0;
    }

    public int minShipSize(int pos, int days, int[] weights) {
        if(days == 0 && pos < weights.length - 1) {
            return Integer.MAX_VALUE;
        }
        if(pos >= weights.length) return 0;

        int minRes = Integer.MAX_VALUE;
        int sum = 0;
        for(int i = pos; i < weights.length; i++) {
            sum += weights[i];
            int curRes = Math.max(sum, minShipSize(i+1, days - 1, weights)); 
            minRes = Math.min(minRes, curRes);
            // System.out.printf("pos = %s, days = %s, i = %s, sum = %s, curRes = %s, minREs = %s \n", pos, days, i, sum, curRes, minRes);
        }
        return minRes;
    }
}
// @lc code=end

