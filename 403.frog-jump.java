/*
 * @lc app=leetcode id=403 lang=java
 *
 * [403] Frog Jump
 *
 * https://leetcode.com/problems/frog-jump/description/
 *
 * algorithms
 * Hard (46.18%)
 * Likes:    5627
 * Dislikes: 255
 * Total Accepted:    278K
 * Total Submissions: 601.4K
 * Testcase Example:  '[0,1,3,5,6,8,12,17]'
 *
 * A frog is crossing a river. The river is divided into some number of units,
 * and at each unit, there may or may not exist a stone. The frog can jump on a
 * stone, but it must not jump into the water.
 * 
 * Given a list of stones positions (in units) in sorted ascending order,
 * determine if the frog can cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assumes the first jump must be
 * 1 unit.
 * 
 * If the frog's last jump was k units, its next jump must be either k - 1, k,
 * or k + 1 units. The frog can only jump in the forward direction.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: stones = [0,1,3,5,6,8,12,17]
 * Output: true
 * Explanation: The frog can jump to the last stone by jumping 1 unit to the
 * 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone,
 * then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the
 * 8th stone.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: stones = [0,1,2,3,4,8,9,11]
 * Output: false
 * Explanation: There is no way to jump to the last stone as the gap between
 * the 5th and 6th stone is too large.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 2^31 - 1
 * stones[0] == 0
 * stones is sorted in a strictly increasing order.
 * 
 * 
 */

// @lc code=start
class Solution {
    int[] nums;
    int N;

    Map<Integer, Integer> stoneIndexMap;
    Boolean[][] mem;


    public boolean canCross(int[] stones) {
        nums = stones;
        N = nums.length;
        
        mem = new Boolean[N][N];
        stoneIndexMap = new HashMap<Integer, Integer>();

        for(int i = 0; i<stones.length; i++) {
            stoneIndexMap.put(stones[i], i);
        }
        return canCrossHelper(0, 0);
    }

    boolean canCrossHelper(int pos, int jump) {
        if(pos >= N) {
            return false;
        } 
        if(pos == N - 1) {
            return true;
        }

        
        if(mem[pos][jump] == null) {
            // boolean res = false;
            boolean way1 = false, way2 = false, way3 = false;
            if(stoneIndexMap.containsKey(nums[pos] + jump) && jump > 0) {
                way1 = canCrossHelper(stoneIndexMap.get(nums[pos] + jump), jump);
            }
            if(stoneIndexMap.containsKey(nums[pos] + jump - 1) && jump - 1 > 0) {
                way2 = canCrossHelper(stoneIndexMap.get(nums[pos] + jump - 1), jump - 1);
            }

            if(stoneIndexMap.containsKey(nums[pos] + jump + 1)) {
                way3 = canCrossHelper(stoneIndexMap.get(nums[pos] + jump + 1), jump + 1);
            }
        
            mem[pos][jump] = way1 || way2 || way3;
        }
        
        return mem[pos][jump];
    } 

    // boolean canCrossHelper(int prev, int pos, int jump) {
    //     if(pos >= N || nums[pos] > nums[prev] + jump || jump < 0) {
    //         return false;
    //     } else if(pos == N -1 &&  nums[prev] + jump == nums[pos]) {
    //         return true;
    //     }
    //     if(mem[prev][pos][jump] == null) {
    //         boolean res = false;
    //         if(nums[pos] < nums[prev] + jump) {
    //             res = canCrossHelper(prev, pos+1, jump);
    //         } else {
    //             boolean way1, way2, way3;
    //             way1 = canCrossHelper(pos, pos+1, jump);
    //             way2 = canCrossHelper(pos, pos+1, jump - 1);
    //             way3 = canCrossHelper(pos, pos+1, jump + 1);
    //             res = way1 || way2 || way3;
    //         }
    //         mem[prev][pos][jump] = res;
    //     }
    //     return mem[prev][pos][jump];
    // } 
}
// @lc code=end

